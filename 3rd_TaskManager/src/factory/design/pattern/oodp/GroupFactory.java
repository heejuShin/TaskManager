package factory.design.pattern.oodp;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import group.management.oodp.Group;
//https://johngrib.github.io/wiki/factory-method-pattern/
import group.management.oodp.GroupDAO;
import group.management.oodp.GroupDTO;

public abstract class GroupFactory {
	public final Group create(String name, int num, String hostName, String[] memberName) {
		Group group = createGroup(name, num, hostName, memberName);
		System.out.println(group.getName());
		registerGroup(group);
		return group;
	}
	public final Group make(String name) {
		GroupDAO groupdao = new GroupDAO();
		GroupDTO groupdto = new GroupDTO();
		groupdto=groupdao.getGroupUsingName(name);
		String saveMem[] = new String[groupdto.getNum()];
	    for(int j=0; j<groupdto.getNum(); j++) {
	    	saveMem[j] = groupdto.getMemberName(j);
	    }
		Group group = createGroup(groupdto.getName(), groupdto.getNum(), groupdto.getHostName(), saveMem);
		return group;
	}
	protected abstract Group createGroup(String name, int num, String hostName, String[] memberName);
	public final void registerGroup(Group group) {
		try {
			BufferedWriter bos = new BufferedWriter(new FileWriter("group.txt", true));
			    bos.write(group.getName()+"/");
			    bos.write(Integer.toString(group.getType())+"/");
			    bos.write(Integer.toString(group.getNum())+"/");
			    bos.write(group.getHostName()+"/"); //host는 만든사람
			    for(int j=0; j<group.getNum()-1; j++) {
			    	bos.write(group.getMemberName(j)+"/");
			    }
			    bos.write("!end!\n");
				bos.close();
				JOptionPane.showMessageDialog(null, group.getName()+" 그룹이 생성되었습니다.");
		}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "그룹 생성에 실패했습니다.");
		}
	}
}
