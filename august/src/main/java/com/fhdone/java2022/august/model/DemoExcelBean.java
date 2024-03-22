package com.fhdone.java2022.august.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class DemoExcelBean {

    /**
     * 我的申请ID
     */
    @Excel(name = "申请单号")
    private Long myApplyId;

    /**
     * 访客姓名
     */
    @Excel(name = "访客姓名*")
    private String visitorName;

    /**
     * 证件类型
     */
    @Excel(name = "证件类型*")
    private String visitorCardType;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码*")
    private String visitorCardCode;

    /**
     * 访问开始日期
     */
    @Excel(name = "访问开始日期*")
    private Date startTime;

    /**
     * 访问结束日期
     */
    @Excel(name = "访问结束日期*")
    private Date endTime;

    /**
     * 访客类型
     */
    @Excel(name = "访客类型")
    private String visitorType;

    /**
     * 访客说明
     */
    @Excel(name = "访客说明")
    private String mark;

    /**
     * 访客单位
     */
    @Excel(name = "访客单位")
    private String visitorOrg;

    /**
     * 访客联系电话
     */
    @Excel(name = "访客联系电话*")
    private String visitorPhone;

    /**
     * 属地ID
     */
    @Excel(name = "办公区域")
    private Long areaId;

    @Excel(name = "访问日期*")
    private String visitDate;

    @Excel(name = "访问开始时间*")
    private String startHour;

    @Excel(name = "访问结束时间*")
    private String endHour;

    @Excel(name = "所进门岗(下拉选择)*")
    private String entranceGate;

    @Excel(name = "所出门岗(下拉选择)*")
    private String exportGate;

    @Excel(name = "是否驾车(下拉选择)")
    private String ifDrive;

    @Excel(name = "车牌颜色(下拉选择)")
    private String plateColor;

    @Excel(name = "车牌号码")
    private String plateNumber;

    @Excel(name = "入场次数")
    private Integer inFactTimes;

    /**
     * "访客单位"，待访客申请里名字是"所属单位"。其实就是一个字段，前端展示不一样
     */
    @Excel(name = "所属单位*")
    private String visitorOrg2;
}
