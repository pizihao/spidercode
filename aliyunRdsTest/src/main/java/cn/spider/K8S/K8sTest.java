package cn.spider.K8S;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class K8sTest {


    private static final String namespace = "bi-kettle";

    public static void main(String[] args) {
        List<Ready> readies = new ArrayList<>();
        boolean isAllReady = readies.stream()
                .anyMatch(c -> !c.getReady());
        System.out.println(isAllReady);

        Float cpuTotal = Optional.ofNullable("1.22")
                .map(Float::valueOf)
                .map(a -> {
                    DecimalFormat format = new DecimalFormat("#.#");
                    return format.format(a);
                })
                .map(Float::valueOf)
                .get();
        System.out.println(cpuTotal);


        DecimalFormat format = new DecimalFormat("#.#");
        System.out.println(format.format(1.266d));
        System.out.println(format.format(1.6005d));
        System.out.println(format.format(451.266d));

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
