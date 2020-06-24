package schedule.management.oodp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

import java.awt.*;
import java.util.*;
import java.text.*;

public class EditSchedule extends JFrame {
	
	public void EditSchedule(UserDTO user,Group group){
		JPanel panel = new JPanel();
		Label l1 = new Label("스케줄 수정");
		add(l1);
		l1.setBounds(40, 10, 80, 30);
		
	
		
		add(panel);
		setSize(500,400);
		setTitle("스케줄 수정");
		setVisible(true);
	}
	

}
