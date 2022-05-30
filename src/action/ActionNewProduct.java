/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import entities.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.MNewProduct;
import util.Restriction;
import util.ResultValidation;
import util.Validation;

/**
 *
 * @author Adrien
 */
public class ActionNewProduct {
    
    public static boolean newProduct(HttpServletRequest request, HttpServletResponse response) {
        
        String[] s_formParamsNeeded = {"productName", "productDescription", "productPrice", "productCategory", "productSerial", "productStock", "productIsActive"};
        String[] s_formValuesNeeded = {
            request.getParameter(s_formParamsNeeded[0]),
            request.getParameter(s_formParamsNeeded[1]),
            request.getParameter(s_formParamsNeeded[2]),
            request.getParameter(s_formParamsNeeded[3]),
            request.getParameter(s_formParamsNeeded[4]),
            request.getParameter(s_formParamsNeeded[5]),
            request.getParameter(s_formParamsNeeded[6])};
        
        boolean isCompleted = true;

        //HashMap des données obligatoire
        HashMap<String, String> hm_formParamValue = new HashMap<String, String>();
        for (int i = 0; i < s_formValuesNeeded.length; i++) {
            hm_formParamValue.put(s_formParamsNeeded[i], s_formValuesNeeded[i]);
        }

        //Valide le formulaire et enregriste les message d'erreur dans la requête
        isCompleted = validateNewProduct(hm_formParamValue, request);

        //On fait maintenant l'insertion des données dans la base de données
        if (isCompleted) {
            Item item = new Item();

            //Utilisez Misc.getOrDefault pour les champs qui sont optionnel, car il se pourrait que sa retourne null
            // Par exemple si on le supprime du formulaire (en éditant la page) et il n'est pas testé plus haut
            item.setName(hm_formParamValue.get("productName"));
            item.setDescription(hm_formParamValue.get("productDescription"));
            item.setPrice(Double.parseDouble(hm_formParamValue.get("productPrice")));
            item.setCategory(Integer.parseInt(hm_formParamValue.get("productCategory")));
            item.setSerial(hm_formParamValue.get("productSerial"));
            item.setStock(Integer.parseInt(hm_formParamValue.get("productStock")));
            boolean isActive = hm_formParamValue.get("productIsActive") != null;
            System.out.println(isActive);
            item.setActive(isActive);

            // item.setActive(Boolean.parseBoolean(isActive));
            int rep = MNewProduct.newProduct(item);

            //Si une erreur est arrivé
            // rep = 1 : le produit n'existe pas
            // rep = 0 : le produit existe
            // rep = -1 : probleme de connexion a la base de donnees
            if (rep < 1) {
                isCompleted = false;
                if (rep == 0) {
                    request.setAttribute("error", "Ce produit existe deja");
                } else if (rep == -1) {
                    request.setAttribute("error", "DBProblem");
                }
            }
        }

        //HashMap pour conserver les valeurs entré par l'utilisateur (on ne le forcera pas à tous réécrire)
        if (!isCompleted) {
            request.setAttribute("hm_formParamValue", hm_formParamValue);
        }
        return isCompleted;
    }
    
    ;
    
    	
	public static boolean validateNewProduct(HashMap<String, String> hm_formParamValue, HttpServletRequest request) {
        //On créer un HashMap pour contenir les potiennelles message d'erreurs
        HashMap<String, String> hm_fieldErrorMsgForNewProduct = new HashMap<String, String>();

        //Création des restriction de validation
        Restriction restrictionName = new Restriction(3, 45, Pattern.compile("^([a-zA-ZàéèêâïçÀÉÈÊÏÇ-[0-9]])+([ -][a-zA-ZàéèêâïçÀÉÈÊÏÇ-[0-9]]]+)*"));
        Restriction restrictionPrice = new Restriction(true, 1, 10, Pattern.compile("[0-9]+([\\.][0-9]+)?"));
        Restriction restrictionDescription = new Restriction(1, 45);
        Restriction restrictionSerial = new Restriction(1, 20, Pattern.compile("[0-9]*"));
        //Restriction restrictionCategory = new Restriction(true, 1, 10, Pattern.compile("[0-9]"));
        Restriction restrictionStock = new Restriction(true, 1, 10, Pattern.compile("[0-9]*"));

        //Création d'un objet Validation et ajout des restrictions à ce dernier
        Validation validation = new Validation(hm_formParamValue);
        validation.addRestriction("productName", restrictionName);
        validation.addRestriction("productPrice", restrictionPrice);
        validation.addRestriction("productDescription", restrictionDescription);
        //validation.addRestriction("productCategory", restrictionCategory);
        validation.addRestriction("productSerial", restrictionSerial);
        validation.addRestriction("productStock", restrictionStock);

        //On conserve les résultat des tests
        ArrayList<ResultValidation> resultValidations = validation.validate();
        //On parcours les résultat des tests
        for (ResultValidation rv : resultValidations) {
            //Si le test ne passe pas, alors on ajoute un message d'erreur
            if (rv.getCode() != 0) {
                hm_fieldErrorMsgForNewProduct.put(rv.getKey(), getErrorMsgForNewProduct(rv));
            }
        }

        //On passe le hashMap en attribut à la requête
        request.setAttribute("hm_fieldErrorMsg", hm_fieldErrorMsgForNewProduct);
        
        return validation.isValidate();
    }
    
    private static String getErrorMsgForNewProduct(ResultValidation resValid) {
        String errorMsg = "";
        switch (resValid.getKey()) {
            case "productName":
                errorMsg += getErrorMsgForProductName(resValid);
                break;
            case "productPrice":
                errorMsg += getErrorMsgForProductPrice(resValid);
                break;
            case "productDescription":
                errorMsg += getErrorMsgForProductDescription(resValid);
                break;
            case "productCategory":
                errorMsg += getErrorMsgForProductCategory(resValid);
                break;
            case "productSerial":
                errorMsg += getErrorMsgForProductSerial(resValid);
                break;
            case "productStock":
                errorMsg += getErrorMsgForProductStock(resValid);
                break;
        }
        
        return errorMsg;
    }
    
    private static String getErrorMsgForProductName(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Nom\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                errorMsg += "Lettres et chiffres seulement, pas de caractère spéciaux. Espace et trait d'union accepté, sauf s'ils ont au début ou à la fin du nom)";
                break;
            case 6:
                break;
        }
        
        return errorMsg;
    }
    
    private static String getErrorMsgForProductPrice(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Prix\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                errorMsg += "Chiffres seulement, pas de lettres ou de caractères spéciaux. La virgule (,) est interdite, utiliser le point (.) seulement";
                break;
            case 5:
                break;
        }
        
        return errorMsg;
    }
    
    private static String getErrorMsgForProductDescription(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Description\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                break;
        }
        
        return errorMsg;
    }

    private static String getErrorMsgForProductCategory(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Categorie\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                break;
        }
        
        return errorMsg;
    }

    private static String getErrorMsgForProductSerial(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Numéro de série\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                break;
        }
        
        return errorMsg;
    }

    private static String getErrorMsgForProductStock(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ \"Quantité en stock\".\n";
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
            case 4:
                break;
        }
        
        return errorMsg;
    }
    
};
