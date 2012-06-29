// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.testscript.Workflow.cfg;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;

/**
 * Script Name   : <b>SampleE2E</b><br>
 * Generated     : <b>2012/03/14 1:58:13 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows XP x86 5.1 build 2600 Service Pack 2 <br>
 * 
 * @since  March 14, 2012
 * @author test
 */
public abstract class SampleE2EHelper extends RationalTestScript
{
	/**
	 * Palette_Root: with default state.
	 *		type : Palette_Root
	 * 		.class : org.eclipse.gef.palette.PaletteRoot
	 * 		.classIndex : 0
	 */
	protected PaletteGuiSubitemTestObject palette_Root() 
	{
		return new PaletteGuiSubitemTestObject(
                        getMappedTestObject("palette_Root"));
	}
	/**
	 * Palette_Root: with specific test context and state.
	 *		type : Palette_Root
	 * 		.class : org.eclipse.gef.palette.PaletteRoot
	 * 		.classIndex : 0
	 */
	protected PaletteGuiSubitemTestObject palette_Root(TestObject anchor, long flags) 
	{
		return new PaletteGuiSubitemTestObject(
                        getMappedTestObject("palette_Root"), anchor, flags);
	}
	
	

	protected SampleE2EHelper()
	{
		setScriptName("testscript.Workflow.cfg.SampleE2E");
	}
	
}

