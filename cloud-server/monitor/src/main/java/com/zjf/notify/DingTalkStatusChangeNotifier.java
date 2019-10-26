package com.zjf.notify;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务状态改变则通知
 * @author Harry
 */
@Slf4j
public class DingTalkStatusChangeNotifier extends AbstractStatusChangeNotifier {
	private static final String DEFAULT_MESSAGE = "#{instance.registration.name} (#{instance.registration.serviceUrl}) is 【#{event.statusInfo.status}】";

	private final SpelExpressionParser parser = new SpelExpressionParser();
    private String webhookToken;
    private List<String> atMobiles;
    private Expression message;

    public DingTalkStatusChangeNotifier(InstanceRepository repository) {
        super(repository);
        this.message = parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }
    
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> sendNotifier(event, instance));
    }

    private void sendNotifier(InstanceEvent event, Instance instance) {
        if (event instanceof InstanceStatusChangedEvent) {
            log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                    ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());

            String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();

            switch (status) {
                // 健康检查没通过
                case "DOWN":
                    sendDingTalk(event, instance, "服务健康检查没通过");
                    break;
                // 服务离线
                case "OFFLINE":
                    sendDingTalk(event, instance, "服务离线");
                    break;
                //服务上线
                case "UP":
                    sendDingTalk(event, instance, "服务上线");
                    break;
                // 服务未知异常
                case "UNKNOWN":
                    sendDingTalk(event, instance, "服务未知异常");
                    break;
                default:
                    break;
            }

        } else {
            log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                    event.getType());
        }



    }

    private void sendDingTalk(InstanceEvent event, Instance instance, String msg){
        String content = this.getMessage(event, instance, msg);
        DingTalkClient client = new DefaultDingTalkClient(webhookToken);
        OapiRobotSendRequest request = new OapiRobotSendRequest();

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        if(atMobiles != null && !atMobiles.isEmpty()){
            at.setAtMobiles(atMobiles);
        }else{
            at.setIsAtAll("true");
        }
        request.setAt(at);

        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setText(text);

        try {
            OapiRobotSendResponse response = client.execute(request);
            if(0 == response.getErrcode()){
                log.info("通知发送成功:{}", content);
            }else if(-1	 == response.getErrcode()){
                //服务器暂不可用，建议稍候再重试1次，最多重试3次
                int i = 0;
                while (i <= 2){
                    try {
                        //10秒后重试
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        log.error("sleep 异常", e);
                    }
                    OapiRobotSendResponse execute = client.execute(request);
                    if(execute.getErrcode() == 0){
                        break;
                    }
                    i++;
                }
                if(i > 2){
                    log.error("钉钉服务器暂不可用errcode:{} errmsg:{}", response.getErrcode(), response.getErrmsg());
                }else{
                    log.info("重试{}次后，发送成功===》errcode:{} errmsg:{}", i, response.getErrcode(), response.getErrmsg());
                }
            }else{
                log.error("通知发送失败errcode:{} errmsg:{}", response.getErrcode(), response.getErrmsg());
            }
        } catch (ApiException e) {
            log.error("通知发送失败", e);
        }
    }


    private String getMessage(InstanceEvent event,Instance instance, String msg) {
    	 Map<String, Object> root = new HashMap<>();
         root.put("event", event);
         root.put("instance", instance);
         root.put("lastStatus", getLastStatus(event.getInstance()));
         root.put("msg", msg);
         StandardEvaluationContext context = new StandardEvaluationContext(root);
         context.addPropertyAccessor(new MapAccessor());
         return message.getValue(context, String.class);
    }

    public String getWebhookToken() {
        return webhookToken;
    }

    public void setWebhookToken(String webhookToken) {
        this.webhookToken = webhookToken;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public Expression getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = (Expression) this.parser.parseExpression(message, ParserContext.TEMPLATE_EXPRESSION);
    }

}