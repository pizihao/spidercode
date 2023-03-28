package cn.spider.K8S;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class K8sClient {

    public static void main(String[] args) {
        getKubernetesClient();
    }

    public static KubernetesClient getKubernetesClient() {
        Config config = new ConfigBuilder().withMasterUrl("https://10.0.33.96:6443")
                .withRequestTimeout(30 * 1000)
                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImZyYW1ld29yay1kZXZlbG9wZXItdG9rZW4teGxuaHAiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZnJhbWV3b3JrLWRldmVsb3BlciIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNkM2ViZGRmLTJmYjktMTFlYy1hMWNmLTAwMTYzZTA0ODkwMCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmZyYW1ld29yay1kZXZlbG9wZXIifQ")
                .withTrustCerts(true)
                .build();

        KubernetesClient client = new DefaultKubernetesClient(config);
        NamespaceList namespaceList = client.namespaces().list();
        System.out.println(namespaceList);
        return client;
    }

}
