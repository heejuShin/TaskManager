package meeting.management.oopd;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
 
public class Meeting_List extends JFrame implements MouseListener,ActionListener{
   
    Vector v;  
    Vector cols;
    DefaultTableModel model;
    JTable jTable;
    JScrollPane pane;
    JPanel pbtn;
    JButton btnInsert;
       
   
    public Meeting_List(){
        super("회의 관리");
        //v=getMemberList();
        //MemberDAO
        MeetingDAO dao = new MeetingDAO();
        v = dao.getMeetingList();
        System.out.println("v="+v);
        cols = getColumn();
       
        //public DefaultTableModel()
        //public DefaultTableModel(int rowCount, int columnCount)
        //public DefaultTableModel(Vector columnNames, int rowCount)
        //public DefaultTableModel(Object[] columnNames, int rowCount)
        //public DefaultTableModel(Vector data,Vector columnNames)
        //public DefaultTableModel(Object[][] data,Object[] columnNames)
       
        model = new DefaultTableModel(v, cols);
       
        //JTable()
        //JTable(int numRows, int numColumns)
        //JTable(Object[][] rowData, Object[] columnNames)
        //JTable(TableModel dm)
        //JTable(TableModel dm, TableColumnModel cm)
        //JTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
        //JTable(Vector rowData, Vector columnNames)
       
        //jTable = new JTable(v,cols);
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();
        btnInsert = new JButton("새 회의 기록 추가");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //리스너 등록
        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
       
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end 생성자
   
   
    //JTable의 컬럼
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("회의 이름");
        col.add("회의 날짜");
        col.add("회의 장소");
        col.add("참가 인원");
        col.add("회의 내용");
       
        return col;
    }//getColumn
   
   
    //Jtable 내용 갱신 메서드
    public void jTableRefresh(){
       
        MeetingDAO dao = new MeetingDAO();
        DefaultTableModel model= new DefaultTableModel(dao.getMeetingList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    public static void main(String[] args) {
        new Meeting_List();
    }//main
    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked 만 사용
        int r = jTable.getSelectedRow();
        String name = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
        MeetingProc mem = new MeetingProc(name,this); //이름을 인자로 수정창 생성
               
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //버튼을 클릭하면
        if(e.getSource() == btnInsert ){
           new MeetingProc (this);
        	
            /*테스트*/
            //dao = new MemberDAO();           
            //dao.userSelectAll(model);
            //model.fireTableDataChanged();
            //jTable.updateUI();           
            //jTable.requestFocusInWindow();
           
        }
       
    }
   
}