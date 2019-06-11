package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.jonas.test.spring.lifecycle.Log.println;

@ConfigurationProperties("com.test.context.aware")
@Slf4j
public class GetInContextAwareProperties {

    public GetInContextAwareProperties() {
        println(log, "", "constructor", "这里注意，这个类是被上面的Configuration启用的。但是因为没有显式注入，而是在setApplicationContext里getBean。所以初始化比上面的Configuration晚。在被用的时候才初始化。");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println(log, "GetInContextAwareProperties", "setName", "可以看到，这里setName被调用。这一阶段，@ConfigurationProperties的数据可以被绑定。");
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetInContextAwareProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
