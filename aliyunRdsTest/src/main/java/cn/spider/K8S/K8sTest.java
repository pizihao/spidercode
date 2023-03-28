package cn.spider.K8S;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;

public class K8sTest {


    private static final String namespace = "bi-kettle";

    public static void main(String[] args) {
        KubernetesClient kubernetesClient = K8sClient.getKubernetesClient();
        PodList podList = kubernetesClient.pods().inNamespace(namespace).withLabel("app=" + "framesample").list();

        System.out.println(podList);
    }
}
