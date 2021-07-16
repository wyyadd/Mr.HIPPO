package com.hippo.fresh.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//指定ES中index名
@Document(indexName = "productindex")
public class SearchProduct{
    @Id // 主键
    private String id;//id

    @Field(type = FieldType.Long, name = "productId")
    private Long productId;//商品id

    @Field(type = FieldType.Integer, name = "categoryId")
    private Integer categoryId;//商品目录id

    @Field(type = FieldType.Text, name = "name")
    private String name;//商品名称

    @Field(type = FieldType.Double, name = "price")
    private Double price;//商品单价

    @Field(type = FieldType.Text, name = "detail")
    private String detail;//商品详细信息

    @Field(type = FieldType.Long, name = "salesAmount")
    private Long salesAmount;//商品销售量
}