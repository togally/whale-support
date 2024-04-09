package com.whale.cloud.router.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CodeGenerateUtils {
    @Value("${whale.datasource.url}")
    private String url;

    @Value("${whale.datasource.username}")
    private String userName;

    @Value("${whale.datasource.password}")
    private String password;

    private String projectPath = "/Users/jiazhiwei/IdeaProjects/WhaleCloud/";

    private String packagePath = "com/whale/cloud/";

    private String packageReference = "com.whale.cloud";

    private List<String> superColumns = List.of("create_time","create_by","last_update_time","last_update_by","is_del","tenant_id","user_id");

    public void generate(String packageName, String servicePath, String entityPath,Class<?> baseClazz,Class<?> controllerClazz,String... table) {
        FastAutoGenerator.create(url, userName, password)
                .globalConfig(builder -> {
                    builder.author("togally") // 设置作者
                            .outputDir(getOutPath(servicePath, "")); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent(packageReference) // 设置父包名
                            .moduleName(packageName) // 设置父包模块名
                            .pathInfo(getCustomPathMap(packageName, servicePath, entityPath));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(table) // 设置需要生成的表名
                            .addTablePrefix("whale_")// 生成代码时过滤前缀
                            .entityBuilder()
                            .enableLombok() // lombok注解
                            .disableSerialVersionUID() // 关闭序列化id生成
                            .logicDeletePropertyName("idDel") // 逻辑删除属性名
                            .enableTableFieldAnnotation() // 字段注解
                            .superClass(baseClazz)
                            .addSuperEntityColumns(superColumns)
                            .controllerBuilder()
                            .superClass(controllerClazz)
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    private Map<OutputFile, String> getCustomPathMap(String packageName, String servicePath, String entityPath) {
        Map<OutputFile, String> pathMap = new HashMap<>(2);
        // xml路径
        pathMap.put(OutputFile.xml, projectPath + servicePath + "/src/main/java/" + packagePath + packageName + "/mapper");
        // entity路径
        pathMap.put(OutputFile.entity, projectPath + entityPath + "/src/main/java/" + packagePath + packageName + "/entity");
        return pathMap;
    }

    private String getOutPath(String modulePath, String packageName) {
        return projectPath + modulePath + "/src/main/java/" + packageName;
    }
}
