package org.yky.test;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum SplitStatusEnum {

    DF_INIT("df_init", "principal", () -> SpringUtil.getBean("principalHandler", FlowHandler.class)),
    DF_PRINCIPAL("df_principal", null, () -> SpringUtil.getBean("feeHandler", FlowHandler.class));

    private static final Map<String, SplitStatusEnum> ALL_MAP;
    static {
        ALL_MAP = Stream.of(values()).collect(Collectors.toMap(SplitStatusEnum::getSplitStatus, Function.identity()));
    }

    // 当前状态
    private String splitStatus;
    // 下一个状态
    private String nextSplitStatus;
    // 当前状态对应的操作
    private FlowHandler flowHandler;

    SplitStatusEnum(String splitStatus, String nextSplitStatus, Supplier<FlowHandler> getFlowHandler) {
        this.splitStatus = splitStatus;
        this.nextSplitStatus = nextSplitStatus;
        this.flowHandler = getFlowHandler.get();
    }

    public SplitStatusEnum next() {
        return ALL_MAP.get(nextSplitStatus);
    }

    public static SplitStatusEnum of(String value) {
        return ALL_MAP.get(value);
    }

    public static void main(String[] args) {
        String init = "df_init";
        SplitStatusEnum nowSpEnum = SplitStatusEnum.of(init);
        nowSpEnum.getFlowHandler().handler();

        SplitStatusEnum next = nowSpEnum.next();
        next.getFlowHandler().handler();
    }

}
