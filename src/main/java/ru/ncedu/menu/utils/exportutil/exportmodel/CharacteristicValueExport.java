package ru.ncedu.menu.utils.exportutil.exportmodel;

import ru.ncedu.menu.models.CharacteristicValue;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CharacteristicValueExport {
    private List<CharacteristicValue> characteristicValues;

    public CharacteristicValueExport(){}
    public CharacteristicValueExport(List<CharacteristicValue> characteristicValues) {

        this.characteristicValues = characteristicValues;
    }

    public List<CharacteristicValue> getCharacteristicValues() {
        return characteristicValues;
    }
    @XmlElement(name = "CharacteristicValues")
    public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }


}
