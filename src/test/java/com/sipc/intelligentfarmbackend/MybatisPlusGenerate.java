//package com.sipc.intelligentfarmbackend;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.TemplateType;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//public class MybatisPlusGenerate {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://127.0.0.1:3306/intelligent_farm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
//        String username = "root";
//        String password = "123456";
//
//        FastAutoGenerator.create(url, username, password)
//                .globalConfig(builder -> builder
//                        .author("gang.yang") // 设置作者
//                        .outputDir("src/main/java") // 指定输出目录
//                        .dateType(DateType.TIME_PACK)
//                        .commentDate("yyyy-MM-dd"))
//                .packageConfig(builder -> {
//                    builder.parent("com.sipc") // 设置父包名
//                            .moduleName("intelligentfarmbackend") // 设置父包模块名
//                            .entity("pojo.domain") //设置entity包名
//                            .mapper("mapper")
//                            .xml("mapper.xml")
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")); // 设置mapperXml生成路径
//
//                })
//                .strategyConfig(builder ->
//                        builder.addInclude(getTables("all"))
//                                //实体类策略配置
//                                .entityBuilder()
//                                .enableLombok()
//                                .enableTableFieldAnnotation()
//                                .logicDeleteColumnName("is_deleted")
//                                .addSuperEntityColumns("created_by", "created_time", "updated_by", "updated_time", "is_deleted")
//                                .idType(IdType.AUTO)
//                                .enableFileOverride()
//                                //mapper类策略配置
//                                .mapperBuilder()
//                                .mapperAnnotation(Mapper.class)
//                                .enableFileOverride()
//                )
//                //模板配置
//                .templateEngine(new FreemarkerTemplateEngine()).templateConfig(builder ->
//                        //禁用相关的模板
//                        builder.disable(TemplateType.CONTROLLER)
//                                .disable(TemplateType.SERVICE)
//                                .disable(TemplateType.SERVICE_IMPL)
//                ).execute();
//    }
//    @SuppressWarnings("all")
//    protected static List<String> getTables(String tables) {
//        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
//    }
//}
