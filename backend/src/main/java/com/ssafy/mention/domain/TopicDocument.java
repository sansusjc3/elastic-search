package com.ssafy.mention.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.Id;


@Document(indexName = "topic")
@Getter
@NoArgsConstructor
@Mapping(mappingPath = "src/main/resources/elastic/topic-mapping.json")
@Setting(settingPath = "src/main/resources/elastic/topic-setting.json")
public class TopicDocument {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;
}
