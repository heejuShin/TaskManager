package group.management.oodp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import factory.design.pattern.oodp.CompanyGroupFactory;
import factory.design.pattern.oodp.GroupFactory;
import factory.design.pattern.oodp.OtherGroupFactory;
import factory.design.pattern.oodp.SchoolGroupFactory;
import user.management.oodp.SignUp;
import user.management.oodp.UserDTO;
import group.management.oodp.Menu;
import state.design.pattern.oodp.Autumn;
import state.design.pattern.oodp.Season;
import state.design.pattern.oodp.Spring;
import state.design.pattern.oodp.Summer;
import state.design.pattern.oodp.Winter;

public class ManageGroup extends JFrame implements ActionListener{
	UserDTO user = new UserDTO();
	GroupDTO group = new GroupDTO();

	private JButton j1,j2,j3,b1,b2,b3,b4;
	private JLabel l1,l2;
	Season season=new Season();
	Spring s1=new Spring();
	Summer s2=new Summer();
	Autumn s3=new Autumn();
	Winter s4=new Winter();
	public Color color;

	public void screen(UserDTO user) {
		setLayout(null);
		l1 = new JLabel("환영합니다.	"+ user.getName() +" 님!!");
		l2 = new JLabel("배경을 바꿉니다. 맘에 드는 계절을 선택해주세요. ");
		add(l1);
		add(l2);
		l1.setBounds(20, 0, 200, 40);
		l2.setBounds(20,120,400,35);
		j1 = new JButton("그룹 생성"); add(j1);
		j2 = new JButton("그룹 관리"); add(j2);
		j3 = new JButton("새로 고침"); add(j3);
		b1 = new JButton("봄"); add(b1);
		b2 = new JButton("여름"); add(b2);
		b3 = new JButton("가을"); add(b3);
		b4 = new JButton("겨울"); add(b4);
	
		j1.setBounds(150, 330, 80, 30);
		j2.setBounds(250, 330, 80, 30);
		j3.setBounds(400, 0, 80, 30);
		b1.setBounds(0,160,80,30);
		b2.setBounds(0,200,80,30);
		b3.setBounds(0,240,80,30);
		b4.setBounds(0,280,80,30);
		j1.addActionListener(this);
		j2.addActionListener(this);
		j3.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		String g_str;
		String[] g_array;
		JButton btn[] = new JButton[100];
		int i=0;
		try {
			BufferedReader groupbuff = new BufferedReader(new FileReader("group.txt"));
			int width=10;
			int height=50;
			while((g_str=groupbuff.readLine())!=null){
				g_array=g_str.split("/");
				int j=3;
				while(!g_array[j].equals("!end!")) {
					if(g_array[j].equals(user.getName())) {
						btn[i]=new JButton("");
						btn[i].setText(g_array[0]);
						add(btn[i]);
						btn[i].setBounds(width, height, 80, 30);
						i++;
						width+=100;
						if(width>500) {
							width=10;
							height+=40;
						}
						break;
					}
					j++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add(panel);
		setSize(500,400);
		setTitle("그룹 생성/관리/선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MakeGroup makegroup = new MakeGroup();
				makegroup.make(user.getName());
			}
		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowGroupInfo groupinfo = new ShowGroupInfo();
				groupinfo.screen(user,color);
				//JOptionPane.showMessageDialog(null, "아직 구현되지 않은 기능입니다ㅠㅠ");
			}
		});
		j3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManageGroup menu = new ManageGroup();
		        menu.screen(user);
			}
		});
		
		
		
		for(int j=0; j<i; j++) {
			btn[j].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
				    String btnName = button.getActionCommand();
					//System.out.println("->"+btnName);
					GroupDAO groupdao = new GroupDAO();
					group=groupdao.getGroupUsingName(btnName);
					
					int type = group.getType();
					GroupFactory factory;
					if(type==0)
						factory = new SchoolGroupFactory();
					else if (type==1)
						factory = new CompanyGroupFactory();
					else
						factory = new OtherGroupFactory();
					Group newG = factory.make(btnName);
					Menu menu = new Menu();
					menu.screen(user, newG, color);
				}
			});
		}
	}	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b1) {
			color=season.setBackground(s1);
		}
		else if(e.getSource()==b2) {
			color=season.setBackground(s2);
		}
		else if(e.getSource()==b3) {
			color=season.setBackground(s3);
		}
		else if(e.getSource()==b4) {
			color=season.setBackground(s4);
		}
		getContentPane().setBackground(color);
	}

}
