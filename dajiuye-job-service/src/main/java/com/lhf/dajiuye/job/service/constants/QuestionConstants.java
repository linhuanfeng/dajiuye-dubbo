package com.lhf.dajiuye.job.service.constants;

public interface QuestionConstants {
    String INDEX_JOB="job";
    String INDEX_NAME_USER_RECORD="user_record";
    String INDEX_NAME_HOT="hot";
    String MAPPING_HOT_TEMPLATE="{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"hotWord\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    String MAPPING_QUESTION_TEMPLATE="{\n" +
            "  \"settings\": {\n" +
            "    \"analysis\": {\n" +
            "      \"analyzer\": {\n" +
            "        \"text_analyzer\":{\n" +
            "          \"tokenizer\":\"ik_max_word\",\n" +
            "          \"filter\":\"py\"\n" +
            "        },\n" +
            "        \"completion_anylyzer\":{\n" +
            "          \"tokenizer\":\"keyword\",\n" +
            "          \"filter\":\"py\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"filter\": {\n" +
            "        \"py\":{\n" +
            "          \"type\":\"pinyin\",\n" +
            "          \"keep_full_pinyin\": false,\n" +
            "          \"keep_joined_full_pinyin\": true,\n" +
            "          \"keep_original\": true,\n" +
            "          \"limit_first_letter_length\": 16,\n" +
            "          \"remove_duplicated_term\": true,\n" +
            "          \"none_chinese_pinyin_tokenize\": false\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }, \n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"title\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"text_analyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\", \n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"description\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"createTime\": {\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"modifiedTime\": {\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"creatorId\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"creatorName\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"commentCount\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"viewCount\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"likeCount\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"tag\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"sticky\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"areaId\": {\n" +
            "        \"type\": \"long\"\n" +
            "      },\n" +
            "      \"areaName\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"img\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"text_analyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"suggestion\":{\n" +
            "        \"type\": \"completion\",\n" +
            "        \"analyzer\": \"completion_anylyzer\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
