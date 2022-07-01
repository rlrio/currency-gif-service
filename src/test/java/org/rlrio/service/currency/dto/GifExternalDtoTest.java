package org.rlrio.service.currency.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GifExternalDtoTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private String jsonRaw;
    private GifExternalDto expectedDto;


    @BeforeEach
    void init() {
        jsonRaw = "{\"data\":[{\"type\":\"gif\",\"id\":\"LdOyjZ7io5Msw\"," +
                "\"url\":\"https://giphy.com/gifs/make-it-rain-get-paid-LdOyjZ7io5Msw\"," +
                "\"slug\":\"make-it-rain-get-paid-LdOyjZ7io5Msw\",\"bitly_gif_url\":\"http://gph.is/1Z95fsM\"," +
                "\"bitly_url\":\"http://gph.is/1Z95fsM\",\"embed_url\":\"https://giphy.com/embed/LdOyjZ7io5Msw\"," +
                "\"username\":\"spongebob\",\"source\":\"https://www.beamly.com/tv-film/5-genius-ways-to-successfully-run-a-business-according-to-mr-krabs?setLocale=au\"," +
                "\"title\":\"Make It Rain Money GIF by SpongeBob SquarePants\",\"rating\":\"g\",\"content_url\":\"\"," +
                "\"source_tld\":\"www.beamly.com\",\"source_post_url\":\"https://www.beamly.com/tv-film/5-genius-ways-to-successfully-run-a-business-according-to-mr-krabs?setLocale=au\"," +
                "\"is_sticker\":0,\"import_datetime\":\"2016-01-08 00:01:07\",\"trending_datetime\":\"2017-05-28 15:29:21\"," +
                "\"images\":{\"original\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"466377\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g\"," +
                "\"mp4_size\":\"161402\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.mp4&ct=g\"," +
                "\"webp_size\":\"287678\",\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.webp&ct=g\",\"frames\":\"7\"," +
                "\"hash\":\"f26c478b7c4b98f4d83abdfdea5e1b43\"},\"downsized\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"466377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g\"}," +
                "\"downsized_large\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"466377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g\"}," +
                "\"downsized_medium\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"466377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g\"}," +
                "\"downsized_small\":{\"height\":\"336\",\"width\":\"449\",\"mp4_size\":\"71139\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy-downsized-small.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy-downsized-small.mp4&ct=g\"}," +
                "\"downsized_still\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"466377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy_s.gif&ct=g\"},\"fixed_height\":{\"height\":\"200\"," +
                "\"width\":\"267\",\"size\":\"143377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200.gif&ct=g\",\"mp4_size\":\"54548\"," +
                "\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200.mp4&ct=g\",\"webp_size\":\"101158\"," +
                "\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200.webp&ct=g\"}," +
                "\"fixed_height_downsampled\":{\"height\":\"200\",\"width\":\"267\",\"size\":\"131102\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200_d.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200_d.gif&ct=g\"," +
                "\"webp_size\":\"107930\",\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200_d.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200_d.webp&ct=g\"}," +
                "\"fixed_height_small\":{\"height\":\"100\",\"width\":\"134\",\"size\":\"47404\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100.gif&ct=g\"," +
                "\"mp4_size\":\"20302\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100.mp4&ct=g\",\"webp_size\":\"36568\"," +
                "\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100.webp&ct=g\"},\"fixed_height_small_still\":{\"height\":\"100\",\"width\":\"134\",\"size\":\"9290\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100_s.gif&ct=g\"}," +
                "\"fixed_height_still\":{\"height\":\"200\",\"width\":\"267\",\"size\":\"26540\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200_s.gif&ct=g\"}," +
                "\"fixed_width\":{\"height\":\"150\",\"width\":\"200\",\"size\":\"94344\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w.gif&ct=g\"," +
                "\"mp4_size\":\"36115\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w.mp4&ct=g\"," +
                "\"webp_size\":\"63724\",\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w.webp&ct=g\"}," +
                "\"fixed_width_downsampled\":{\"height\":\"150\",\"width\":\"200\",\"size\":\"81737\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w_d.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w_d.gif&ct=g\"," +
                "\"webp_size\":\"66814\",\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w_d.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w_d.webp&ct=g\"}," +
                "\"fixed_width_small\":{\"height\":\"75\",\"width\":\"100\",\"size\":\"28449\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100w.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100w.gif&ct=g\"," +
                "\"mp4_size\":\"13154\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100w.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100w.mp4&ct=g\"," +
                "\"webp_size\":\"22016\",\"webp\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100w.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100w.webp&ct=g\"}," +
                "\"fixed_width_small_still\":{\"height\":\"75\",\"width\":\"100\",\"size\":\"5446\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/100w_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=100w_s.gif&ct=g\"}," +
                "\"fixed_width_still\":{\"height\":\"150\",\"width\":\"200\",\"size\":\"15603\"," +
                "\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/200w_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=200w_s.gif&ct=g\"}," +
                "\"looping\":{\"mp4_size\":\"3429683\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy-loop.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy-loop.mp4&ct=g\"}," +
                "\"original_still\":{\"height\":\"374\",\"width\":\"500\",\"size\":\"92793\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy_s.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy_s.gif&ct=g\"}," +
                "\"original_mp4\":{\"height\":\"358\",\"width\":\"480\",\"mp4_size\":\"161402\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.mp4&ct=g\"}," +
                "\"preview\":{\"height\":\"184\",\"width\":\"246\",\"mp4_size\":\"32035\",\"mp4\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy-preview.mp4?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy-preview.mp4&ct=g\"}," +
                "\"preview_gif\":{\"height\":\"91\",\"width\":\"122\",\"size\":\"49974\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy-preview.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy-preview.gif&ct=g\"}," +
                "\"preview_webp\":{\"height\":\"120\",\"width\":\"160\",\"size\":\"37720\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy-preview.webp?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy-preview.webp&ct=g\"}," +
                "\"480w_still\":{\"height\":\"359\",\"width\":\"480\",\"size\":\"466377\",\"url\":\"https://media2.giphy.com/media/LdOyjZ7io5Msw/480w_s.jpg?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=480w_s.jpg&ct=g\"}}," +
                "\"user\":{\"avatar_url\":\"https://media0.giphy.com/avatars/spongebob/U4nimQFVGMqR.jpg\",\"banner_image\":\"\",\"banner_url\":\"\",\"profile_url\":\"https://giphy.com/spongebob/\",\"username\":\"spongebob\"," +
                "\"display_name\":\"SpongeBob SquarePants\",\"description\":\"Who gifs in a pineapple under the sea?\",\"instagram_url\":\"\",\"website_url\":\"http://www.spongebob.com\",\"is_verified\":true}," +
                "\"analytics_response_payload\":\"e=Z2lmX2lkPUxkT3lqWjdpbzVNc3cmZXZlbnRfdHlwZT1HSUZfU0VBUkNIJmNpZD1mYzNkM2Q4Y3hwaWhsdHN2OHh5bmdpaDR2YjRyNTNzeHF1aG85eDhwdWI3OTNlenImY3Q9Zw\"," +
                "\"analytics\":{\"onload\":{\"url\":\"https://giphy-analytics.giphy.com/v2/pingback_simple?analytics_response_payload=e%3DZ2lmX2lkPUxkT3lqWjdpbzVNc3cmZXZlbnRfdHlwZT1HSUZfU0VBUkNIJmNpZD1mYzNkM2Q4Y3hwaWhsdHN2OHh5bmdpaDR2YjRyNTNzeHF1aG85eDhwdWI3OTNlenImY3Q9Zw&action_type=SEEN\"}," +
                "\"onclick\":{\"url\":\"https://giphy-analytics.giphy.com/v2/pingback_simple?analytics_response_payload=e%3DZ2lmX2lkPUxkT3lqWjdpbzVNc3cmZXZlbnRfdHlwZT1HSUZfU0VBUkNIJmNpZD1mYzNkM2Q4Y3hwaWhsdHN2OHh5bmdpaDR2YjRyNTNzeHF1aG85eDhwdWI3OTNlenImY3Q9Zw&action_type=CLICK\"}," +
                "\"onsent\":{\"url\":\"https://giphy-analytics.giphy.com/v2/pingback_simple?analytics_response_payload=e%3DZ2lmX2lkPUxkT3lqWjdpbzVNc3cmZXZlbnRfdHlwZT1HSUZfU0VBUkNIJmNpZD1mYzNkM2Q4Y3hwaWhsdHN2OHh5bmdpaDR2YjRyNTNzeHF1aG85eDhwdWI3OTNlenImY3Q9Zw&action_type=SENT\"}}}]," +
                "\"pagination\":{\"total_count\":10907,\"count\":1,\"offset\":0},\"meta\":{\"status\":200,\"msg\":\"OK\",\"response_id\":\"xpihltsv8xyngih4vb4r53sxquho9x8pub793ezr\"}}";

        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("type", "gif");
        data1.put("id", "LdOyjZ7io5Msw");
        data1.put("url", "https://giphy.com/gifs/make-it-rain-get-paid-LdOyjZ7io5Msw");
        data1.put("slug", "make-it-rain-get-paid-LdOyjZ7io5Msw");

        Map<String, Object> images = new HashMap<>();
        Map<String, Object> original = new HashMap<>();
        original.put("url", "https://media2.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=fc3d3d8cxpihltsv8xyngih4vb4r53sxquho9x8pub793ezr&rid=giphy.gif&ct=g");
        original.put("size", "466377");
        images.put("original", original);
        data1.put("images", images);
        data.add(data1);

        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("total_count", 10907);
        pagination.put("count", 1);
        pagination.put("offset", 0);

        Map<String, Object> meta = new HashMap<>();
        meta.put("status", 200);
        meta.put("msg", "OK");
        meta.put("response_id", "xpihltsv8xyngih4vb4r53sxquho9x8pub793ezr");

        expectedDto = new GifExternalDto();
        expectedDto.setData(data);
        expectedDto.setPagination(pagination);
        expectedDto.setMeta(meta);
    }

    @Test
    void testMappingToGifExternalDto() throws JsonProcessingException {
        GifExternalDto actualDto = mapper.readValue(jsonRaw, GifExternalDto.class);
        Map<String, Object> dataExpected = expectedDto.getData().get(0);
        Map<String, Object> dataActual = actualDto.getData().get(0);
        Map<String, Object> imagesExpected = (Map<String, Object>) dataExpected.get("images");
        Map<String, Object> originalExpected = (Map<String, Object>) imagesExpected.get("original");

        Map<String, Object> imagesActual = (Map<String, Object>) dataActual.get("images");
        Map<String, Object> originalActual = (Map<String, Object>) imagesActual.get("original");

        Map<String, Integer> paginationExpected = expectedDto.getPagination();
        Map<String, Integer> paginationActual = actualDto.getPagination();

        Map<String, Object> metaExpected = expectedDto.getMeta();
        Map<String, Object> metaActual = actualDto.getMeta();

        assertEquals(expectedDto.getData().size(), actualDto.getData().size());
        assertEquals(dataExpected.get("type"), dataActual.get("type"));
        assertEquals(dataExpected.get("id"), dataActual.get("id"));
        assertEquals(dataExpected.get("url"), dataActual.get("url"));
        assertEquals(dataExpected.get("slug"), dataActual.get("slug"));

        assertEquals(originalExpected.get("url"), originalActual.get("url"));
        assertEquals(originalExpected.get("size"), originalActual.get("size"));

        assertEquals(paginationExpected.get("total_count"), paginationActual.get("total_count"));
        assertEquals(paginationExpected.get("count"), paginationActual.get("count"));
        assertEquals(paginationExpected.get("offset"), paginationActual.get("offset"));

        assertEquals(metaExpected.get("status"), metaActual.get("status"));
        assertEquals(metaExpected.get("msg"), metaActual.get("msg"));
        assertEquals(metaExpected.get("response_id"), metaActual.get("response_id"));
    }
}
