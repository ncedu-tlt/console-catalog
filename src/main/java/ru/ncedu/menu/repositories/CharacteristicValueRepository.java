package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexanderZheleznov on 12.11.2016.
 * Создаёт репозиторий в JSON формате.
 * Сохжраняют такие сущности как:
 */
public class CharacteristicValueRepository implements Repository<CharacteristicValue>{

    private static final String FILE_NAME = "characteristicValues.json";

    private static CharacteristicValueRepository instance;

    private List <CharacteristicValue> characteristicValues;

    private CharacteristicValueRepository(){
    }

    public static synchronized CharacteristicValueRepository getInstance(){
        if(instance == null){
            instance = new CharacteristicValueRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<CharacteristicValue> get() {
        return characteristicValues;
    }

//    public CharacteristicValue get(long id_characteristic){
//        for(CharacteristicValue value : characteristicValues){
//            if(value.getCharacteristicId() == id_characteristic){
//                return value;
//            }
//        }
//        return null;
//    }

    public CharacteristicValue get(long id_product){
        for(CharacteristicValue value : characteristicValues){
            if(value.getProductId() == id_product){
                return value;
            }
        }
        return null;
    }

    @Override
    public CharacteristicValue add(CharacteristicValue object) {
        if (object == null)
            return null;

        CharacteristicValue characteristicValue = new CharacteristicValue(object);
        characteristicValue.setProductId(characteristicValue.getProductId());
        characteristicValue.setCharacteristicId(characteristicValue.getCharacteristicId());

        characteristicValues.add(characteristicValue);
        return characteristicValue;
    }

    @Override
    public CharacteristicValue update(CharacteristicValue object) {
        if(object == null)
            return null;

        CharacteristicValue characteristicValue = new CharacteristicValue(object);
        remove(characteristicValue);

        characteristicValues.add(characteristicValue);
        return characteristicValue;
    }

    @Override
    public void remove(CharacteristicValue objectCharacteristicValue) {
        if(objectCharacteristicValue == null)
            return;

        CharacteristicValue characteristicValue = get(objectCharacteristicValue.getProductId());
        characteristicValues.remove(characteristicValue);
    }

    public void remove(long characteristicId) {
        List<CharacteristicValue> characteristicValuesToRemove = new ArrayList<>();

        if (characteristicId == 0) return;

        for (CharacteristicValue characteristicValue: characteristicValues) {
            if (characteristicValue.getCharacteristicId() == characteristicId) {
                characteristicValuesToRemove.add(characteristicValue);
            }
        }
        characteristicValues.addAll(characteristicValuesToRemove);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, characteristicValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            characteristicValues = JSONUtils.readListFromFile(FILE_NAME, CharacteristicValue.class);
        } catch (IOException e) {
            characteristicValues = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
