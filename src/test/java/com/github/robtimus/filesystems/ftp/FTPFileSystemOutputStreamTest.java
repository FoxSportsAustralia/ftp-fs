/*
 * FTPFileSystemOutputStreamTest.java
 * Copyright 2016 Rob Spoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.robtimus.filesystems.ftp;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;
import org.mockftpserver.fake.filesystem.FileEntry;

@SuppressWarnings({ "nls", "javadoc" })
public class FTPFileSystemOutputStreamTest extends AbstractFTPFileSystemTest {

    @Test
    public void testWriteSingle() throws IOException {

        try (OutputStream output = getFileSystem().newOutputStream(createPath("/foo"))) {
            output.write('H');
            output.write('e');
            output.write('l');
            output.write('l');
            output.write('o');
        }
        FileEntry file = getFile("/foo");
        assertEquals("Hello", getStringContents(file));
    }

    @Test
    public void testWriteBulk() throws IOException {

        try (OutputStream output = getFileSystem().newOutputStream(createPath("/foo"))) {
            output.write("Hello".getBytes());
        }
        FileEntry file = getFile("/foo");
        assertEquals("Hello", getStringContents(file));
    }
}