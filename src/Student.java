import java.util.concurrent.TimeUnit;

interface DoHomeWork{
	void doHomeWork(String question,  String answer);
}

public class Student implements DoHomeWork{

@Override
public void doHomeWork(String question, String answer) {
    System.out.println("��ҵ��");
    if(answer != null) {
        System.out.println("��ҵ��"+question+" �𰸣�"+ answer);
    } else {
        System.out.println("��ҵ��"+question+" �𰸣�"+ "(�հ�)");
    }
}

public void ask(final String homework, final RoomMate roomMate) {
    new Thread(new Runnable() {

        @Override
        public void run() {
            roomMate.getAnswer(homework, Student.this);
        }
    }).start();

    goHome();
}

public void goHome(){
    System.out.println("�һؼ��ˡ��������ѣ�����д����ҵ��");
}

public static void main(String[] args) {
    Student student = new Student();
    String homework = "��x������0��sin(x)/x =?";
    student.ask(homework, new RoomMate());

}
}

class RoomMate {

    public void getAnswer(String homework, DoHomeWork someone) {
        if ("1+1=?".equals(homework)) {
            someone.doHomeWork(homework, "2");
        } else if("��x������0��sin(x)/x =?".equals(homework)) {

            System.out.print("˼����");
            for(int i=1; i<=3; i++) {
                System.out.print(i+"�� ");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            someone.doHomeWork(homework, "1");
        } else {
            someone.doHomeWork(homework, "(�հ�)");
        }
    }

}
