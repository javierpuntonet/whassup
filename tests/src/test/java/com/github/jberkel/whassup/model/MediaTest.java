package com.github.jberkel.whassup.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.github.jberkel.whassup.model.Fixtures.fileToBytes;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MediaTest {
    @Test
    public void testDeserializeThumbImage() throws Exception {
        Media media = new Media();
        media.thumb_image = fileToBytes(Fixtures.THUMB_IMAGE);

        assertThat(media.getFile()).isNotNull();
        assertThat(media.getFile().getAbsolutePath()).isEqualTo("/storage/emulated/0/WhatsApp/Media/WhatsApp Images/IMG-20130526-WA0000.jpg");
        assertThat(media.getFileSize()).isEqualTo(67731L);
    }

    @Test
    public void shouldHandleSerializedDataOfWrongType() throws Exception {
        Media media = new Media();
        media.thumb_image = fileToBytes(Fixtures.VECTOR_SERIALIZED);
        assertThat(media.getFile()).isNull();
    }

    @Test
    public void shouldHandleInvalidSerializedData() throws Exception {
        Media media = new Media();
        media.thumb_image = new byte[] {(byte) 0xde, (byte) 0xad, (byte) 0xbe, (byte) 0xef};
        assertThat(media.getFile()).isNull();
    }
}
