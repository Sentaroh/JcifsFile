使用方法

//SMB1 for jcifs-1.3.17
JcifsAuth auth_smb1=new JcifsAuth(JcifsFile.JCIFS_LEVEL_JCIFS1, domain, username, userpassword);

//SMB2 by jcifs-ng
JcifsAuth auth_smb2=new JcifsAuth(JcifsFile.JCIFS_LEVEL_JCIFS2, domain, username, userpassword);

JcifsFile jf_smb1=new JcifsFile("smb://192.168.0.10/share/readme.txt", auth_smb1);

JcifsFile jf_smb2=new JcifsFile("smb://192.168.0.10/share/readme.txt", auth_smb2);

System.out.println("exists="+jf_smb1.exists())

System.out.println("exists="+jf_smb2.exists())