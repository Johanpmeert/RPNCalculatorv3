package sample;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Stack;
import static java.lang.Math.sqrt;

public class Controller {

    public Label stackl0;
    public Label stackl1;
    public Label stackl2;
    public Label stackl3;
    public Label stackl4;
    public Label stackl5;
    public Label stackl6;
    public Label stackl7;
    public Label stackdepth;
    public Label toetsdruk;
    private Stack<Double> rpnstack = new Stack<Double>();
    private String ingavelijn="";

    public void verwerktoets(KeyEvent event) {
        toetsdruk.setText(event.getCode().toString());
        if ((event.getCode()==KeyCode.NUMPAD0)||(event.getCode()==KeyCode.DIGIT0)) {
            pushb0();
        } else if ((event.getCode()==KeyCode.NUMPAD1)||(event.getCode()==KeyCode.DIGIT1)) {
            pushb1();
        } else if ((event.getCode()==KeyCode.NUMPAD2)||(event.getCode()==KeyCode.DIGIT2)) {
            pushb2();
        } else if ((event.getCode()==KeyCode.NUMPAD3)||(event.getCode()==KeyCode.DIGIT3)) {
            pushb3();
        } else if ((event.getCode()==KeyCode.NUMPAD4)||(event.getCode()==KeyCode.DIGIT4)) {
            pushb4();
        } else if ((event.getCode()==KeyCode.NUMPAD5)||(event.getCode()==KeyCode.DIGIT5)) {
            pushb5();
        } else if ((event.getCode()==KeyCode.NUMPAD6)||(event.getCode()==KeyCode.DIGIT6)) {
            pushb6();
        } else if ((event.getCode()==KeyCode.NUMPAD7)||(event.getCode()==KeyCode.DIGIT7)) {
            pushb7();
        } else if ((event.getCode()==KeyCode.NUMPAD8)||(event.getCode()==KeyCode.DIGIT8)) {
            pushb8();
        } else if ((event.getCode()==KeyCode.NUMPAD9)||(event.getCode()==KeyCode.DIGIT9)) {
            pushb9();
        } else if (event.getCode()==KeyCode.BACK_SPACE) {
            pushbs();
        } else if (event.getCode()==KeyCode.ADD) {
            pushplus();
        } else if (event.getCode()==KeyCode.SUBTRACT) {
            pushminus();
        } else if (event.getCode()==KeyCode.MULTIPLY) {
            pushmultiply();
        } else if (event.getCode()==KeyCode.DIVIDE) {
            pushdivision();
        } else if (event.getCode()==KeyCode.DECIMAL) {
            pushstop();
        } else if (event.getCode()==KeyCode.E) {
            pusheex();
        }
    }

    public void pushb0() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="0";
        } else {
            ingavelijn = ingavelijn + "0";
        }
        toonlijn0();
    }

    public void pushb1() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="1";
        } else {
            ingavelijn = ingavelijn + "1";
        }
        toonlijn0();
    }

    public void pushb2() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="2";
        } else {
            ingavelijn = ingavelijn + "2";
        }
        toonlijn0();
    }

    public void pushb3() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="3";
        } else {
            ingavelijn = ingavelijn + "3";
        }
        toonlijn0();
    }

    public void pushb4() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="4";
        } else {
            ingavelijn = ingavelijn + "4";
        }
        toonlijn0();
    }

    public void pushb5() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="5";
        } else {
            ingavelijn = ingavelijn + "5";
        }
        toonlijn0();
    }

    public void pushb6() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="6";
        } else {
            ingavelijn = ingavelijn + "6";
        }
        toonlijn0();
    }

    public void pushb7() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="7";
        } else {
            ingavelijn = ingavelijn + "7";
        }
        toonlijn0();
    }

    public void pushb8() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="8";
        } else {
            ingavelijn = ingavelijn + "8";
        }
        toonlijn0();
    }

    public void pushb9() {
        if (ingavelijn.isEmpty()) {
            ingavelijn="9";
        } else {
            ingavelijn = ingavelijn + "9";
        }
        toonlijn0();
    }

    public void pushstop() {
        if (ingavelijn.isEmpty()) {
            ingavelijn=".";
        } else {
            ingavelijn = ingavelijn + ".";
        }
        toonlijn0();
    }

    public void pushenter() {
        if (ingavelijn.length()>0) {
            try {
                Double ingavedouble = Double.parseDouble(ingavelijn);
                rpnstack.push(ingavedouble);
                ingavelijn="";
            } catch (NumberFormatException e) {
            } finally {
                toonstack();
                toonlijn0();
            }
        }
    }

    public void pushsquare() {
        pushenter();
        if (rpnstack.size()>0) {
            double interim1 = rpnstack.pop();
            rpnstack.push(interim1*interim1);
            toonstack();
        }
        toonlijn0();
    }

    public void pushsquareroot() {
        pushenter();
        if (rpnstack.size()>0) {
            rpnstack.push(sqrt(rpnstack.pop()));
            toonstack();
        }
        toonlijn0();
    }

    public void push1x() {
        pushenter();
        if (rpnstack.size()>0) {
            rpnstack.push(1/rpnstack.pop());
            toonstack();
        }
        toonlijn0();
    }

    public void pushplusminus() {
        if ((ingavelijn.length()==0)&&(rpnstack.size()>0)) {
            rpnstack.push((-rpnstack.pop())); // it's on the stack, just inverse sign
            toonstack();
        }
        if (ingavelijn.length()>0) {
            if (ingavelijn.contains("E")) {
                if (ingavelijn.contains("E-")) {  // E- is here, make it E+
                    ingavelijn=ingavelijn.replace("E-", "E+");
                } else if (ingavelijn.contains("E+")) {  // E+ is here, make it E-
                    ingavelijn=ingavelijn.replace("E+", "E-");
                } else {  // E is here, make it E-
                    ingavelijn=ingavelijn.substring(0,ingavelijn.indexOf("E")+1) + "-" + ingavelijn.substring(ingavelijn.indexOf("E")+1,ingavelijn.length()) ;
                }
            } else if (ingavelijn.contains("-")) {  // no E so just change sign in front
                ingavelijn = ingavelijn.substring(1, ingavelijn.length());
            } else {
                ingavelijn = "-" + ingavelijn;
            }
            toonlijn0();
        }
    }

    public void pushplus() {
        pushenter();
        if (rpnstack.size()>1) {
            rpnstack.push(rpnstack.pop()+rpnstack.pop());
        }
        toonstack();
    }

    public void pushminus() {
        pushenter();
        if (rpnstack.size()>1) {
            rpnstack.push(-rpnstack.pop()+rpnstack.pop());
        }
        toonstack();
    }

    public void pushmultiply() {
        pushenter();
        if (rpnstack.size()>1) {
            rpnstack.push(rpnstack.pop()*rpnstack.pop());
        }
        toonstack();
    }

    public void pushdivision() {
        pushenter();
        if (rpnstack.size()>1) {
            rpnstack.push(1/rpnstack.pop()*rpnstack.pop());
        }
        toonstack();
    }

    public void pusheex() {
        if (!ingavelijn.contains("E")) {
            ingavelijn=ingavelijn+"E";
            toonlijn0();
        }
    }

    public void pushdup() {
        pushenter();
        if (rpnstack.size()>0) {
            double interim = rpnstack.pop();
            rpnstack.push(interim);
            rpnstack.push(interim);
        }
        toonstack();
    }

    public void pushrot() {
        pushenter();
        if (rpnstack.size()>1) {
            double interim1 = rpnstack.pop();
            double interim2 = rpnstack.pop();
            rpnstack.push(interim1);
            rpnstack.push(interim2);
        }
        toonstack();
    }

    public void pushover() {
        pushenter();
        if (rpnstack.size()>2) {
            double interim1 = rpnstack.pop();
            double interim2 = rpnstack.pop();
            double interim3 = rpnstack.pop();
            rpnstack.push(interim2);
            rpnstack.push(interim1);
            rpnstack.push(interim3);
        }
        toonstack();
    }

    public void pushce() {
        ingavelijn="";
        toonlijn0();
    }

    public void pushbs() {
        if (ingavelijn.length()>0) {
            ingavelijn=ingavelijn.substring(0,ingavelijn.length()-1);
            toonlijn0();
        } else if (rpnstack.size()>0) {
            rpnstack.pop();
            toonstack();
        }
    }

    public void pushclr() {
        pushce();
        rpnstack.clear();
        toonstack();
    }

    public void toonlijn0() {
        stackl0.setText(ingavelijn);
    }

    public void toonstack() {
        int stackdiepte = rpnstack.size();
        if (stackdiepte>=1) {
            stackl1.setText(String.valueOf(rpnstack.get(stackdiepte-1)));
        } else {
            stackl1.setText("<empty>");
        }
        if (stackdiepte>=2) {
            stackl2.setText(String.valueOf(rpnstack.get(stackdiepte-2)));
        } else {
            stackl2.setText("<empty>");
        }
        if (stackdiepte>=3) {
            stackl3.setText(String.valueOf(rpnstack.get(stackdiepte-3)));
        }else {
            stackl3.setText("<empty>");
        }
        if (stackdiepte>=4) {
            stackl4.setText(String.valueOf(rpnstack.get(stackdiepte-4)));
        } else {
            stackl4.setText("<empty>");
        }
        if (stackdiepte>=5) {
            stackl5.setText(String.valueOf(rpnstack.get(stackdiepte-5)));
        } else {
            stackl5.setText("<empty>");
        }
        if (stackdiepte>=6) {
            stackl6.setText(String.valueOf(rpnstack.get(stackdiepte-6)));
        }else {
            stackl6.setText("<empty>");
        }
        if (stackdiepte>=7) {
            stackl7.setText(String.valueOf(rpnstack.get(stackdiepte-7)));
        } else {
            stackl7.setText("<empty>");
        }
        stackdepth.setText(String.valueOf(stackdiepte));
    }

}
