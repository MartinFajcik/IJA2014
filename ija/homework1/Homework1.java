
package ija.homework1;

import ija.homework1.tape.*;
import ija.homework1.objects.*;

import java.lang.reflect.*;

/**
 * Homework1: uloha c. 1 z IJA
 * Trida testujici implementaci zadani 1. ukolu.
 */
public class Homework1 {

    public static void main(String[] argv) {
        testTapeObjects();
        testTapeHead();
        testTapeField();
        testClasses();
        System.out.println("OK");
    }

    /** Test tridy TapeObject a odvozenych */
    public static void testTapeObjects() {
        TapeObject w11 = new Wall("wall1");
        TapeObject w12 = new Wall("wall1");
        TapeObject w21 = new Wall("wall2");
        TapeObject g11 = new Gate("gate1");
        TapeObject g12 = new Gate("gate1");
        TapeObject g21 = new Gate("gate2");

        assert ! w11.canSeize()                   : "do zdi nelze vstoupit";
        assert ! w11.open()                       : "zed nelze otevrit";
        assert ! w11.canSeize()                   : "do zdi nelze vstoupit";

        assert ! g11.canSeize()                   : "brana je zavrena, nelze vstoupit";
        assert g11.open()                         : "branu lze otevrit";
        assert g11.canSeize()                     : "brana je otevrena, nelze vstoupit";
        assert ! g11.open()                       : "branu nelze otevrit (uz je otevrena)";
        assert w11.equals(w12)                    : "w11 a w12 jsou stejne";
        assert w11.hashCode() == w12.hashCode()   : "w11 a w12 maji stejny hashCode";
        assert w12.equals(w11)                    : "w12 a w11 jsou stejne";
        assert ! w11.equals(w21)                  : "w11 a w21 nejsou stejne";

        assert ! g11.equals(g12)                  : "g11 a g12 nejsou stejne (g11 je zavrena a g12 je otevrena)";
        assert g12.open()                         : "branu lze otevrit";
        assert g11.equals(g12)                    : "g11 a g12 jsou stejne (obe brany jsou otevrene)";
        assert g11.hashCode() == g12.hashCode()   : "g11 a g12 maji stejny hashCode";
        assert ! g11.equals(g21)                  : "w11 a w21 nejsou stejne";
    }

    /** Test tridy TapeHead */
    public static void testTapeHead() {
        TapeHead h11 = new TapeHead(1);
        TapeHead h12 = new TapeHead(1);
        TapeHead h21 = new TapeHead(2);

        assert h11.id() == h12.id()               : "h11 a h12 maji stejny identifikator";
        assert h11.equals(h12)                    : "h11 a h12 jsou stejne";
        assert h11.hashCode() == h12.hashCode()   : "h11 a h12 maji stejny hashCode";

        assert h11.id() != h21.id()               : "h11 a h21 maji ruzny identifikator";
        assert ! h11.equals(h21)                  : "h11 a h21 nejsou stejne";
    }

    /** Test tridy TapeField */
    public static void testTapeField() {
        TapeObject w1 = new Wall("wall1");
        TapeObject g1 = new Gate("gate1");
        TapeHead h1 = new TapeHead(1);
        TapeHead h2 = new TapeHead(2);

        TapeField f11 = new TapeField(1);
        TapeField f12 = new TapeField(1);
        TapeField f13 = new TapeField(1, w1);

        assert f11.position() == f12.position()   : "f11 a f12 reprezentuji stejnou pozici";
        assert f11.equals(f12)                    : "f11 a f12 jsou stejne";
        assert f11.hashCode() == f12.hashCode()   : "f11 a f12 maji stejny hashCode";

        assert f11.position() == f13.position()   : "f11 a f13 reprezentuji stejnou pozici";
        assert ! f11.equals(f13)                  : "f11 a f13 nejsou stejne (f13 obsahuje objekt zed)";

        TapeField f10 = new TapeField(1);
        TapeField f20 = new TapeField(2, w1);
        TapeField f30 = new TapeField(3, g1);

        assert ! f10.open()                       : "f10 nelze otevrit (neni co)";
        assert f10.canSeize()                     : "f10 lze obsadit";
        assert f10.seize(h1)                      : "obsazeni f10 - lze";
        assert ! f10.canSeize()                   : "f10 nelze obsadit (uz je obsazeno)";
        assert ! f10.seize(h2)                    : "opetovne obsazeni f10 - nelze (uz je obsazeno)";
        assert f10.leave().equals(h1)             : "uvolneni f10";
        assert f10.canSeize()                     : "f10 lze obsadit (bylo uvolneno)";
        assert f10.leave() == null                : "pokus o uvolneni f10 - vraci null (neni obsazeno)";

        assert ! f20.canSeize()                   : "f20 nelze obsadit (zed)";
        assert ! f20.open()                       : "f20 nelze otevrit (neni co)";
        assert ! f20.canSeize()                   : "f20 nelze obsadit (zed)";
        assert ! f20.seize(h1)                    : "obsazeni f20 - nelze (zed)";

        assert ! f30.canSeize()                   : "f30 nelze obsadit (zavrena brana)";
        assert ! f30.seize(h1)                    : "obsazeni f30 - nelze";
        assert f30.open()                         : "f30 lze otevrit";
        assert ! f30.open()                       : "f30 nelze otevrit (uz je otevrena)";
        assert f30.canSeize()                     : "f30 lze obsadit";
        assert f30.seize(h2)                      : "obsazeni f30 - lze";
        assert ! f30.seize(h1)                    : "opetovne obsazeni f30 - nelze (uz je obsazeno)";
        assert f30.leave().equals(h2)             : "uvolneni f30";
        assert f30.canSeize()                     : "f30 lze obsadit (bylo uvolneno)";
        assert f30.leave() == null                : "pokus o uvolneni f30 - vraci null (neni obsazeno)";
    }


    /** Test parametru trid */
    public static void testClasses() {
        assert Modifier.isAbstract(TapeObject.class.getModifiers()) : "Trida TapeObject ma byt abstraktni.";
        try {
            assert Modifier.isAbstract(TapeObject.class.getDeclaredMethod("canSeize").getModifiers()) : "Metoda TapeObject.canSeize() ma byt abstraktni.";
            assert Modifier.isAbstract(TapeObject.class.getDeclaredMethod("open").getModifiers())     : "Metoda TapeObject.open() ma byt abstraktni.";
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            assert false : "Chyby v deklaraci metod u tridy TapeObject";
        }
    }

}