package com.sentaroh.jcifs;

/*
The MIT License (MIT)
Copyright (c) 2018 Sentaroh

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;


public class JcifsFile {

    final static public int JCIFS_FILE_SMB1 = JcifsAuth.JCIFS_FILE_SMB1;
    final static public int JCIFS_FILE_SMB201 = JcifsAuth.JCIFS_FILE_SMB201;
    final static public int JCIFS_FILE_SMB211 = JcifsAuth.JCIFS_FILE_SMB211;
    final static public int JCIFS_FILE_SMB212 = JcifsAuth.JCIFS_FILE_SMB212;
    final static public int JCIFS_FILE_SMB214 = JcifsAuth.JCIFS_FILE_SMB214;
    final static public int JCIFS_FILE_SMB_LATEST = JcifsAuth.JCIFS_FILE_SMB_LATEST;

    private int mSmbLevel = JCIFS_FILE_SMB1;

    private JcifsAuth mAuth = null;

    private jcifsng.smb.SmbFile mSmb201File = null;
    private jcifsng211.smb.SmbFile mSmb211File = null;
    private jcifsng212.smb.SmbFile mSmb212File = null;
    private jcifsng214.smb.SmbFile mSmb214File = null;
    private jcifs.smb.SmbFile mSmbLatestFile = null;
    private jcifs13.smb.SmbFile mSmb1File = null;

    public JcifsFile(String url, JcifsAuth auth) throws MalformedURLException, JcifsException {
        if (auth==null) {
            throw new JcifsException("JcifsAuth is null.");
        }
        mSmbLevel = auth.getSmbLevel();
        mAuth = auth;

        if (mSmbLevel==JCIFS_FILE_SMB1) {
            mSmb1File = new jcifs13.smb.SmbFile(url, auth.getSmb1Auth());
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            mSmb201File = new jcifsng.smb.SmbFile(url, auth.getSmb201Auth());
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            mSmb211File = new jcifsng211.smb.SmbFile(url, auth.getSmb211Auth());
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            mSmb212File = new jcifsng212.smb.SmbFile(url, auth.getSmb212Auth());
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            mSmb214File = new jcifsng214.smb.SmbFile(url, auth.getSmb214Auth());
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            mSmbLatestFile = new jcifs.smb.SmbFile(url, auth.getSmbLatestAuth());
        }
    }

    private JcifsFile(jcifs13.smb.SmbFile smb1File, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB1;
        if (auth==null || !auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB2.");
        }
        mAuth = auth;
        mSmb1File = smb1File;
    }

    private JcifsFile(jcifsng.smb.SmbFile smb2File, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB201;
        if (auth==null || auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB1.");
        }
        mAuth = auth;
        mSmb201File = smb2File;
    }

    private JcifsFile(jcifsng211.smb.SmbFile smb211File, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB211;
        if (auth==null || auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB1.");
        }
        mAuth = auth;
        mSmb211File = smb211File;
    }

    private JcifsFile(jcifsng212.smb.SmbFile smb212File, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB212;
        if (auth==null || auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB1.");
        }
        mAuth = auth;
        mSmb212File = smb212File;
    }

    private JcifsFile(jcifsng214.smb.SmbFile smb214File, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB214;
        if (auth==null || auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB1.");
        }
        mAuth = auth;
        mSmb214File = smb214File;
    }

    private JcifsFile(jcifs.smb.SmbFile smbLatestFile, JcifsAuth auth) throws JcifsException {
        mSmbLevel = JCIFS_FILE_SMB_LATEST;
        if (auth==null || auth.isSmb1()) {
            throw new JcifsException("JcifsAuth is null or SMB1.");
        }
        mAuth = auth;
        mSmbLatestFile = smbLatestFile;
    }

    public boolean isSmb1File() {
        return mSmbLevel==JCIFS_FILE_SMB1;
    }

    public boolean isSmb201File() {
        return mSmbLevel==JCIFS_FILE_SMB201;
    }

    public boolean isSmb211File() {
        return mSmbLevel==JCIFS_FILE_SMB211;
    }

    public boolean isSmb212File() {
        return mSmbLevel==JCIFS_FILE_SMB212;
    }

    public boolean isSmb214File() {
        return mSmbLevel==JCIFS_FILE_SMB214;
    }

    public boolean isSmbLatestFile() {
        return mSmbLevel==JCIFS_FILE_SMB_LATEST;
    }

    public jcifs13.smb.SmbFile getSmb1File() {
        return mSmb1File;
    }

    public jcifsng.smb.SmbFile getSmb201File() {
        return mSmb201File;
    }

    public jcifsng211.smb.SmbFile getSmb211File() {
        return mSmb211File;
    }

    public jcifsng212.smb.SmbFile getSmb212File() {
        return mSmb212File;
    }

    public jcifsng214.smb.SmbFile getSmb214File() {
        return mSmb214File;
    }

    public jcifs.smb.SmbFile getSmbLatestFile() {
        return mSmbLatestFile;
    }

    public boolean exists() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
                return mSmb1File.exists();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
                return mSmb201File.exists();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
                return mSmb211File.exists();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
                return mSmb212File.exists();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
                return mSmb214File.exists();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
                return mSmbLatestFile.exists();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }

    }

    public void delete() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.delete();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.delete();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.delete();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.delete();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.delete();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.delete();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }

    }

    public void mkdir() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.mkdir();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.mkdir();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.mkdir();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.mkdir();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.mkdir();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.mkdir();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public void mkdirs() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.mkdirs();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.mkdirs();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.mkdirs();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.mkdirs();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.mkdirs();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.mkdirs();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public int getAttributes() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.getAttributes();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.getAttributes();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.getAttributes();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.getAttributes();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.getAttributes();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.getAttributes();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public InputStream getInputStream() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.getInputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.getInputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.getInputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.getInputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.getInputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.getInputStream();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (IOException e) {
            throw (new JcifsException(e, 0, e.getCause()));
        }
    }


    public OutputStream getOutputStream() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.getOutputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.getOutputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.getOutputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.getOutputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.getOutputStream();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.getOutputStream();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (IOException e) {
            throw (new JcifsException(e, 0, e.getCause()));
        }
    }

    public void close() throws JcifsException {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	//Nop for JCIFS(SMB1)
//        	mSmb1File.close();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	mSmb201File.close();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	mSmb211File.close();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	mSmb212File.close();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	mSmb214File.close();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	mSmbLatestFile.close();
        } else 
        	throw (new JcifsException("Unknown SMB Level"));
    }

    public void connect() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.connect();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.connect();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.connect();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.connect();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.connect();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.connect();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (IOException e) {
            throw (new JcifsException(e, 0, e.getCause()));
        }
    }

    public void createNew() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.createNewFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.createNewFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.createNewFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.createNewFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.createNewFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.createNewFile();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public String getName() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getName();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getName();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getName();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getName();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getName();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getName();
        }
        return null;
    }

    public String getPath() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getPath();
        }
        return null;
    }

    public String getCanonicalPath() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getCanonicalPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getCanonicalPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getCanonicalPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getCanonicalPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getCanonicalPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getCanonicalPath();
        }
        return null;
    }

    public String getShare() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getShare();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getShare();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getShare();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getShare();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getShare();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getShare();
        }
        return null;
    }

    public int getType() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.getType();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.getType();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.getType();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.getType();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.getType();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.getType();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public String getUncPath() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getUncPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getUncPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getUncPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getUncPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getUncPath();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getUncPath();
        }
        return null;
    }

    public String getParent() {
        if (mSmbLevel==JCIFS_FILE_SMB1) {
        	return mSmb1File.getParent();
        } else if (mSmbLevel==JCIFS_FILE_SMB201) {
        	return mSmb201File.getParent();
        } else if (mSmbLevel==JCIFS_FILE_SMB211) {
        	return mSmb211File.getParent();
        } else if (mSmbLevel==JCIFS_FILE_SMB212) {
        	return mSmb212File.getParent();
        } else if (mSmbLevel==JCIFS_FILE_SMB214) {
        	return mSmb214File.getParent();
        } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
        	return mSmbLatestFile.getParent();
        }
        return null;
    }

    public boolean canRead() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.canRead();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.canRead();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.canRead();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.canRead();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.canRead();
            }  else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.canRead();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean canWrite() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.canWrite();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.canWrite();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.canWrite();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.canWrite();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.canWrite();
            }  else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.canWrite();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean isDirectory() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.isDirectory();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.isDirectory();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.isDirectory();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.isDirectory();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.isDirectory();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.isDirectory();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean isFile() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.isFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.isFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.isFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.isFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.isFile();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.isFile();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean isHidden() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.isHidden();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.isHidden();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.isHidden();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.isHidden();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.isHidden();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.isHidden();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public long length() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.length();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.length();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.length();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.length();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.length();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.length();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public String[] list() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.list();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.list();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.list();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.list();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.list();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.list();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public JcifsFile[] listFiles() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
                jcifs13.smb.SmbFile[] smb1Files = mSmb1File.listFiles();
                if (smb1Files == null) return null;
                JcifsFile[] result = new JcifsFile[smb1Files.length];
                for (int i = 0; i < smb1Files.length; i++)
                    result[i] = new JcifsFile(smb1Files[i], mAuth);
                return result;
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
                jcifsng.smb.SmbFile[] smb201Files = mSmb201File.listFiles();
                if (smb201Files == null) return null;
                JcifsFile[] result = new JcifsFile[smb201Files.length];
                for (int i = 0; i < smb201Files.length; i++)
                    result[i] = new JcifsFile(smb201Files[i], mAuth);
                return result;
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
                jcifsng211.smb.SmbFile[] smb211Files = mSmb211File.listFiles();
                if (smb211Files == null) return null;
                JcifsFile[] result = new JcifsFile[smb211Files.length];
                for (int i = 0; i < smb211Files.length; i++)
                    result[i] = new JcifsFile(smb211Files[i], mAuth);
                return result;
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
                jcifsng212.smb.SmbFile[] smb212Files = mSmb212File.listFiles();
                if (smb212Files == null) return null;
                JcifsFile[] result = new JcifsFile[smb212Files.length];
                for (int i = 0; i < smb212Files.length; i++)
                    result[i] = new JcifsFile(smb212Files[i], mAuth);
                return result;
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
                jcifsng214.smb.SmbFile[] smb214Files = mSmb214File.listFiles();
                if (smb214Files == null) return null;
                JcifsFile[] result = new JcifsFile[smb214Files.length];
                for (int i = 0; i < smb214Files.length; i++)
                    result[i] = new JcifsFile(smb214Files[i], mAuth);
                return result;
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
                jcifs.smb.SmbFile[] smbLatestFiles = mSmbLatestFile.listFiles();
                if (smbLatestFiles == null) return null;
                JcifsFile[] result = new JcifsFile[smbLatestFiles.length];
                for (int i = 0; i < smbLatestFiles.length; i++)
                    result[i] = new JcifsFile(smbLatestFiles[i], mAuth);
                return result;
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public void renameTo(JcifsFile d) throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
                if (d.getSmb1File() == null)
                    throw new JcifsException("Null SMB1 file specified.");
                else mSmb1File.renameTo(d.getSmb1File());
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
                if (d.getSmb201File() == null)
                    throw new JcifsException("Null SMB201 file specified.");
                else mSmb201File.renameTo(d.getSmb201File());
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
                if (d.getSmb211File() == null)
                    throw new JcifsException("Null SMB211 file specified.");
                else mSmb211File.renameTo(d.getSmb211File());
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
                if (d.getSmb212File() == null)
                    throw new JcifsException("Null SMB212 file specified.");
                else mSmb212File.renameTo(d.getSmb212File());
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
                if (d.getSmb214File() == null)
                    throw new JcifsException("Null SMB214 file specified.");
                else mSmb214File.renameTo(d.getSmb214File());
            }  else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
                if (d.getSmbLatestFile() == null)
                    throw new JcifsException("Null SMBLatest file specified.");
                else mSmbLatestFile.renameTo(d.getSmbLatestFile());
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public JcifsAuth getAuth() {
        return mAuth;
    }


    public void setLastModified(long lm) throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	mSmb1File.setLastModified(lm);
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	mSmb201File.setLastModified(lm);
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	mSmb211File.setLastModified(lm);
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	mSmb212File.setLastModified(lm);
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	mSmb214File.setLastModified(lm);
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	mSmbLatestFile.setLastModified(lm);
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public long getLastModified() throws JcifsException {
        try {
            if (mSmbLevel==JCIFS_FILE_SMB1) {
            	return mSmb1File.lastModified();
            } else if (mSmbLevel==JCIFS_FILE_SMB201) {
            	return mSmb201File.lastModified();
            } else if (mSmbLevel==JCIFS_FILE_SMB211) {
            	return mSmb211File.lastModified();
            } else if (mSmbLevel==JCIFS_FILE_SMB212) {
            	return mSmb212File.lastModified();
            } else if (mSmbLevel==JCIFS_FILE_SMB214) {
            	return mSmb214File.lastModified();
            } else if (mSmbLevel==JCIFS_FILE_SMB_LATEST) {
            	return mSmbLatestFile.lastModified();
            } else 
            	throw (new JcifsException("Unknown SMB Level"));
        } catch (jcifsng.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng211.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng212.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifsng214.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs13.smb.SmbException e) {
            throw (new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

}
