package com.sentaroh.jcifs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import jcifs.smb.SmbException;


public class JcifsFile {

    static final public String JCIFS_LEVEL_JCIFS1="CIFS1";
    static final public String JCIFS_LEVEL_JCIFS2="CIFS2";
    private String mLevel=JCIFS_LEVEL_JCIFS1;

    private String mUrl="";

    private JcifsAuth mAuth=null;

    private jcifsng.smb.SmbFile mNewGenerationSmbFile=null;
    private jcifs.smb.SmbFile mTraditionalSmbFile=null;

    public JcifsFile(String url, JcifsAuth auth) throws MalformedURLException {
        mLevel=auth.getCifsLevel();
        mUrl=url;
        mAuth=auth;
        
    	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
    		mTraditionalSmbFile=new jcifs.smb.SmbFile(mUrl, auth.getSmb1Auth());
    	} else {
    		mNewGenerationSmbFile=new jcifsng.smb.SmbFile(mUrl,auth.getSmb2Auth());
    	}
        
    }

    public boolean isSmb1File() {
    	return mLevel.equals(JCIFS_LEVEL_JCIFS1)?true:false;
    }
    
    public boolean exists()   throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.exists();
        	} else {
        		return mNewGenerationSmbFile.exists();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public void delete()   throws JcifsException{
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		mTraditionalSmbFile.delete();
        	} else {
        		mNewGenerationSmbFile.delete();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public void mkdir()   throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		mTraditionalSmbFile.mkdir();
        	} else {
        		mNewGenerationSmbFile.mkdir();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public void mkdirs()   throws JcifsException{
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		mTraditionalSmbFile.mkdirs();
        	} else {
        		mNewGenerationSmbFile.mkdirs();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public int getAttributes() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.getAttributes();
        	} else {
        		return mNewGenerationSmbFile.getAttributes();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    }
    
    public InputStream getInputStream()   throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.getInputStream();
        	} else {
        		return mNewGenerationSmbFile.getInputStream();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (IOException e) {
			e.printStackTrace();
			throw(new JcifsException(e, 0, e.getCause()));
		}
    	
    }


    public OutputStream getOutputStream()  throws JcifsException{
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.getOutputStream();
        	} else {
        		return mNewGenerationSmbFile.getOutputStream();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (IOException e) {
			e.printStackTrace();
			throw(new JcifsException(e, 0, e.getCause()));
		}
    	
    }
    
    public void connect() throws JcifsException{
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		mTraditionalSmbFile.connect();
        	} else {
        		mNewGenerationSmbFile.connect();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (IOException e) {
			e.printStackTrace();
			throw(new JcifsException(e, 0, e.getCause()));
		}

    }

    public void createNew() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		mTraditionalSmbFile.createNewFile();
        	} else {
        		mNewGenerationSmbFile.createNewFile();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }
    
    public String getName() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getName();
		} else {
			return mNewGenerationSmbFile.getName();
		}
    	
    }

    public String getPath() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getPath();
		} else {
			return mNewGenerationSmbFile.getPath();
		}
    }

    public String getCanonicalPath() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getCanonicalPath();
		} else {
			return mNewGenerationSmbFile.getCanonicalPath();
		}
    }

    public String getShare() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getShare();
		} else {
			return mNewGenerationSmbFile.getShare();
		}
    }

    public int getType() throws JcifsException {
        try {
            if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
    			return mTraditionalSmbFile.getType();
    		} else {
    			return mNewGenerationSmbFile.getType();
    		}
        } catch (jcifsng.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public String getUncPath() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getUncPath();
		} else {
			return mNewGenerationSmbFile.getUncPath();
		}
    }

    public String getParent() {
        if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
			return mTraditionalSmbFile.getParent();
		} else {
			return mNewGenerationSmbFile.getParent();
		}
    	
    }

    public boolean canRead()   throws JcifsException {
        try {
            if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
                return mTraditionalSmbFile.canRead();
            } else {
                return mNewGenerationSmbFile.canRead();
            }
        } catch (jcifsng.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean canWrite()   throws JcifsException {
        try {
            if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
                return mTraditionalSmbFile.canWrite();
            } else {
                return mNewGenerationSmbFile.canWrite();
            }
        } catch (jcifsng.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        } catch (jcifs.smb.SmbException e) {
            e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
        }
    }

    public boolean isDirectory() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.isDirectory();
        	} else {
        		return mNewGenerationSmbFile.isDirectory();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public boolean isFile() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.isFile();
        	} else {
        		return mNewGenerationSmbFile.isFile();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public boolean isHidden() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.isHidden();
        	} else {
        		return mNewGenerationSmbFile.isHidden();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    }
    
    public long length() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		return mTraditionalSmbFile.length();
        	} else {
        		return mNewGenerationSmbFile.length();
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }
    
    public String[] list() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) return mTraditionalSmbFile.list();
        	else return mNewGenerationSmbFile.list();
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    	
    }

    public JcifsFile[] listFiles() throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		jcifs.smb.SmbFile[] files=mTraditionalSmbFile.listFiles();
        		if (files==null) return null;
        		JcifsFile[] result=new JcifsFile[files.length];
        		for(int i=0;i<files.length;i++) result[i]=new JcifsFile(files[i].getPath(),mAuth);
        		return result;
        	} else {
                jcifsng.smb.SmbFile[] files=mNewGenerationSmbFile.listFiles();
        		if (files==null) return null;
        		JcifsFile[] result=new JcifsFile[files.length];
        		for(int i=0;i<files.length;i++) result[i]=new JcifsFile(files[i].getPath(),mAuth);
        		return result;
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
            throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (MalformedURLException e) {
            return null;
        }
    }
    
    public void renameTo ( JcifsFile d ) throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        		jcifs.smb.SmbFile to=new jcifs.smb.SmbFile(d.getPath(), d.getAuth().getSmb1Auth());
        		mTraditionalSmbFile.renameTo(to);
        	} else {
                jcifsng.smb.SmbFile to=new jcifsng.smb.SmbFile(d.getPath(), d.getAuth().getSmb2Auth());
        		mNewGenerationSmbFile.renameTo(to);
        	}
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public JcifsAuth getAuth() {
    	return mAuth;
    }

    
    public void setLastModified(long lm) throws JcifsException {
        try {
        	if (mLevel.equals(JCIFS_LEVEL_JCIFS1)) {
        	    mTraditionalSmbFile.setLastModified(lm);
            } else {
        	    mNewGenerationSmbFile.setLastModified(lm);
            }
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    }

    public long getLastModified() throws JcifsException {
        try {
			return mLevel.equals(JCIFS_LEVEL_JCIFS1)?mTraditionalSmbFile.lastModified():mNewGenerationSmbFile.lastModified();
		} catch (jcifsng.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		} catch (jcifs.smb.SmbException e) {
			e.printStackTrace();
			throw(new JcifsException(e, e.getNtStatus(), e.getCause()));
		}
    }


}
