package cn.spider.K8S;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class K8sTest {


    private static final String namespace = "bi-kettle";

    public static void main(String[] args) {
        List<Ready> readies = new ArrayList<>();
        boolean isAllReady = readies.stream()
                .anyMatch(c -> !c.getReady());
        System.out.println(isAllReady);

    }

    static class Ready {

        private boolean ready;

        public Ready(boolean ready) {
            this.ready = ready;
        }

        public boolean getReady() {
            return ready;
        }

        public void setReady(boolean ready) {
            this.ready = ready;
        }
    }
}
