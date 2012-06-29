// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.testscript.Workflow.Supplement;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;

/**
 * Script Name   : <b>S645781_Delete_Multi_Fields</b><br>
 * Generated     : <b>2012/03/12 10:37:58 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows XP x86 5.1 build 2600 Service Pack 2 <br>
 * 
 * @since  March 12, 2012
 * @author ffan
 */
public abstract class S645781_Delete_Multi_FieldsHelper extends RationalTestScript
{
	/**
	 * OptimizeForAppearance: with default state.
	 *		.mappableClassIndex : 1
	 * 		text : Optimize for &appearance
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.priorLabel : Generated clients will have a basic look-and-feel but will perform optimally.
	 * 		.classIndex : 1
	 */
	protected ToggleGUITestObject optimizeForAppearance() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("optimizeForAppearance"));
	}
	/**
	 * OptimizeForAppearance: with specific test context and state.
	 *		.mappableClassIndex : 1
	 * 		text : Optimize for &appearance
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.priorLabel : Generated clients will have a basic look-and-feel but will perform optimally.
	 * 		.classIndex : 1
	 */
	protected ToggleGUITestObject optimizeForAppearance(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("optimizeForAppearance"), anchor, flags);
	}
	
	/**
	 * OptimizeForPerformance: with default state.
	 *		.mappableClassIndex : 0
	 * 		text : Optimize for &performance
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject optimizeForPerformance() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("optimizeForPerformance"));
	}
	/**
	 * OptimizeForPerformance: with specific test context and state.
	 *		.mappableClassIndex : 0
	 * 		text : Optimize for &performance
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.classIndex : 0
	 */
	protected ToggleGUITestObject optimizeForPerformance(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("optimizeForPerformance"), anchor, flags);
	}
	
	/**
	 * ShowTheIntroductionPageWhenOpeningTheDesigner: with default state.
	 *		.mappableClassIndex : 2
	 * 		text : Show the 'Introduction' page when opening the designer.
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.priorLabel : Generated clients will have a robust, sophisticated look-and-feel (on all platfo ...
	 * 		.classIndex : 2
	 */
	protected ToggleGUITestObject showTheIntroductionPageWhenOpe() 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("showTheIntroductionPageWhenOpe"));
	}
	/**
	 * ShowTheIntroductionPageWhenOpeningTheDesigner: with specific test context and state.
	 *		.mappableClassIndex : 2
	 * 		text : Show the 'Introduction' page when opening the designer.
	 * 		.class : org.eclipse.swt.widgets.Button
	 * 		.priorLabel : Generated clients will have a robust, sophisticated look-and-feel (on all platfo ...
	 * 		.classIndex : 2
	 */
	protected ToggleGUITestObject showTheIntroductionPageWhenOpe(TestObject anchor, long flags) 
	{
		return new ToggleGUITestObject(
                        getMappedTestObject("showTheIntroductionPageWhenOpe"), anchor, flags);
	}
	
	

	protected S645781_Delete_Multi_FieldsHelper()
	{
		setScriptName("testscript.Workflow.Supplement.S645781_Delete_Multi_Fields");
	}
	
}

