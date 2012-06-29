package component.entity;

import com.rational.test.ft.script.RationalTestScript;
import component.entity.model.LocalMbo;
import component.entity.model.Operation;
import component.entity.model.Relationship;
import component.entity.model.RestWSMbo;
import component.entity.model.SAPMBO;
import component.entity.model.WSMBO;
import component.entity.model.WizardRunner;
import component.entity.wizard.PaletteOperationCreationWizard;
import component.entity.wizard.PaletteSAPCW;
import component.entity.wizard.PaletteWSCW;
import component.entity.wizard.RestWSMboCreationWizard;

public class Palette extends RationalTestScript{
	public static void createLocalMbo(LocalMbo mbo){
		new PaletteLocalMBOCW().create(mbo, new WizardRunner());
	}

	public static void createRelationship(Relationship relationship) {
		new PaletteRelationshipCW().create(relationship, new WizardRunner());		
	}

	public static void createSAPMbo(SAPMBO mbo) {
		new PaletteSAPCW().create(mbo, new WizardRunner());
		
	}
	
	public static void createWSMbo(WSMBO mbo){
		new PaletteWSCW().create(mbo, new WizardRunner());
	}

	public static void createMbo(Object mbo) {
		if(mbo instanceof LocalMbo){
			createLocalMbo((LocalMbo)mbo);
		}
		else if(mbo instanceof Relationship){
			createRelationship((Relationship)mbo);
		}
		else if(mbo instanceof WSMBO){
			createWSMbo((WSMBO)mbo);
		}
		else if(mbo instanceof RestWSMbo){
			createRestWSMbo((RestWSMbo)mbo);
		}
		else if(mbo instanceof SAPMBO){
			createSAPMbo((SAPMBO)mbo);
		}else{
			throw new RuntimeException("Palette don't know how to create type: "+mbo.getClass());
		}
		
	}

	private static void createRestWSMbo(RestWSMbo mbo) {
		new RestWSMboCreationWizard().create(mbo, new WizardRunner());
		
	}

	public static void createOperation(Operation operation) {
		new PaletteOperationCreationWizard().create(operation, new WizardRunner());
		
	}


}
