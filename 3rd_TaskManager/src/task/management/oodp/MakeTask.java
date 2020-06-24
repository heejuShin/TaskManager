package task.management.oodp;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

public class MakeTask extends JFrame{
	boolean isIdUnique=false;
	int i=0;
	public void MakeTask(UserDTO user,Group group) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		
		Label l1=new Label("작업 이름");
		Label l2=new Label("시작 날짜(YY.MM.DD)");
		Label l3=new Label("종료 날짜(YY.MM.DD)");
		Label l4=new Label("할당할 멤버 선택 - 한 번에 선택 후 > 버튼을 눌러주세요!");
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		//setBounds(x,y,width,height)
		l1.setBounds(40, 10, 60, 30);
		l2.setBounds(40, 50, 140, 30);
		l3.setBounds(40, 80, 140, 30);
		l4.setBounds(40, 110, 300, 30);
		
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		add(t1);
		add(t2);
		add(t3);
		
		t1.setBounds(120, 10, 150, 30);
		t2.setBounds(180, 50, 70, 30);
		t3.setBounds(180, 80, 70, 30);
		
		// 그룹에 등록된멤버들을 선택해서 작업 할당 
		// **나중에 user.text 말고 group.txt로 바꿔야 함. **
		BufferedReader logbuff = null;
		try {
			logbuff = new BufferedReader(new FileReader("user.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str;
		String[] array;
		String userNames[] = new String[100];
		try {
			while((str=logbuff.readLine())!=null){
				
				array=str.split("/");	
				if(!array[0].equals(user.getName())) {
					userNames[i]=array[0];
					i++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JList userJList;
		JList copyJList = new JList();
		JButton copyJButton;
		userJList = new JList( userNames );
		userJList.setVisibleRowCount( 10 );
		userJList.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		JScrollPane userScroll = new JScrollPane(userJList);
		add(userScroll);
		userScroll.setBounds(100, 160, 120, 200);
		copyJButton = new JButton( ">");
		add(copyJButton);
		copyJButton.setBounds(240, 200, 20, 20);
		copyJList.setVisibleRowCount( 10 );
		copyJList.setFixedCellWidth( 120 );
		copyJList.setFixedCellHeight( 15 );
		copyJList.setSelectionMode
		   (
		    ListSelectionModel.SINGLE_INTERVAL_SELECTION );
			copyJButton.addActionListener(
					new ActionListener(){
					   public void actionPerformed(ActionEvent e) {
					    {
					      copyJList.setListData( userJList.getSelectedValues() ); //copyJList에 선택된 값을 넘김.
					     }   
					   	}
					   }	
				);
		JScrollPane copyScroll = new JScrollPane(copyJList); 
		add(copyScroll);
		copyScroll.setBounds(270, 160, 140, 100);
		
		//등록 취소 버튼  
		JButton j1 = new JButton("등록");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);
		j1.setBounds(125, 380, 80, 30);
		j2.setBounds(240, 380, 80, 30);
		add(panel);
		setSize(500,450);
		setTitle("업무 등록 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		// 등록 버튼 눌렀을 때 
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bos = new BufferedWriter(new FileWriter("task.txt", true));
					    bos.write(group.getName()+"/");
					    bos.write(t1.getText()+"/");
					    bos.write(t2.getText()+"/");
					    bos.write(t3.getText()+"/");
					    
					    for(int j=0; j<copyJList.getModel().getSize(); j++) {
					    	bos.write(copyJList.getModel().getElementAt(j)+"/");
					    }
					    bos.write("!end!\n");
						bos.close();
						JOptionPane.showMessageDialog(null, t1.getText()+" 작업이 등록되었습니다.");
						dispose();
				}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "작업 등록이 실패했습니다.");
				}
			}
		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
}
