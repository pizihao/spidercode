package cn.spider.K8S;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class K8sClient {


    public static final String PROMETHUS_CONTAINER_CPU_USAGE = "sum(irate(container_cpu_usage_seconds_total{%s}[3m])*100)" +
            "by(pod,container)/sum(container_spec_cpu_quota{%s}/container_spec_cpu_period{%s})by(pod,container)";

    public static void main(String[] args) {
        String s = "container='gateway'";
        String format = String.format(PROMETHUS_CONTAINER_CPU_USAGE, s, s, s);
        System.out.println(format);


        String queryCondition = "sum(irate(container_cpu_usage_seconds_total{container='gateway'}[3m])*100)by(pod,container)/sum(container_spec_cpu_quota{container='gateway'}/container_spec_cpu_period{container='gateway'})by(pod,container)";
        try {
            String encode = URLEncoder.encode(queryCondition);
            System.out.println("http://192.168.128.155:30091/" + encode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static KubernetesClient getKubernetesClient() {
        Config config = new ConfigBuilder().withMasterUrl("https://10.0.33.96:6443")
                .withRequestTimeout(30 * 1000)
                .withOauthToken("")
                .withTrustCerts(true)
                .build();

        KubernetesClient client = new DefaultKubernetesClient(config);
        NamespaceList namespaceList = client.namespaces().list();
        return client;
    }

}
