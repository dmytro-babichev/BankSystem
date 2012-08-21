package customtags;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author laory
 */
public class AddButtons extends TagSupport {
    private String account;
    
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public int doStartTag() throws JspException {
        FileInputStream fis = null;
        try {
            JspWriter writer = pageContext.getOut();
            fis = new FileInputStream("/home/laory/NetBeansProjects/BankSystem/web/templates/buttons");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                if (str.contains("@@@")) str = str.replace("@@@", account);
                writer.write(str);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddButtons.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddButtons.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(AddButtons.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return SKIP_BODY;
    }
}