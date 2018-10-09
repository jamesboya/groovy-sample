package com.example;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MetricsTest {
    @Test
    public void foo() throws MalformedURLException {
        String url = "http://localhost:8080/api/metrics";
        String path = new URL(url).getPath();
        System.out.println(path);
    }
//    private SimpleMeterRegistry registry;
//
//    @Before
//    public void setup() {
//        registry = new SimpleMeterRegistry();
//    }
//
//    @Test
//    public void foo() throws InterruptedException {
//        Counter counter = Counter.builder("com.example.foo.counter")
//                .description("this is a counter for foo")
//                .baseUnit("count")
//                .register(registry);
//        counter.increment();
//
//        Timer timer = Timer.builder("com.example.foo.timer")
//                .description("this is a timer for foo")
//                .publishPercentiles(.50, .80, .90, .95, .99)
//                .register(registry);
//
//        int runSeconds = 5;
//        int stopMillis = 100;
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(() -> {
//            Random random = new Random();
//            for (int i = 0; i < runSeconds * stopMillis; i++) {
//                timer.record(random.nextInt(100), TimeUnit.MILLISECONDS);
//                try {
//                    Thread.sleep(stopMillis);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        executorService.awaitTermination(runSeconds, TimeUnit.SECONDS);
//
//        Map<String, Map<String, Double>> map = registry.getMeters()
//                .stream()
//                .collect(toMap(this::getMetricName, this::getMetricValue));
//
//        Map<String, Map<String, Double>> sortedMap = map.entrySet().stream()
//                .sorted(Comparator.comparing(Map.Entry::getKey))
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (s1, s2) -> {
//                            throw new IllegalStateException();
//                        },
//                        LinkedHashMap::new));
//
//        System.out.println(sortedMap);
//    }
//
//    private String getMetricName(Meter meter) {
//        Meter.Id id = meter.getId();
//        String metricName = id.getName();
//        if (id.getTags().size() == 0) {
//            return metricName;
//        } else {
//            String tagSuffix = id.getTags().stream()
//                    .flatMap(tag -> Arrays.asList(tag.getKey(), tag.getValue()).stream())
//                    .collect(joining("."));
//            return metricName + "." + tagSuffix;
//        }
//    }
//
//    private Map<String, Double> getMetricValue(Meter meter) {
//        return StreamSupport.stream(meter.measure().spliterator(), false)
//                .collect(toMap(m -> m.getStatistic().toString(), Measurement::getValue));
//    }
//
}
