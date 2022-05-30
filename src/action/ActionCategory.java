package action;

import entities.Category;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MCategory;
import util.Restriction;
import util.ResultValidation;
import util.Validation;

public class ActionCategory {

    public static void getCategories(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("categories", MCategory.getCategories());
    }

    public static void getCategory(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        request.setAttribute("category", MCategory.getCategory(id));
    }

    public static int getSelectedCategory(HttpServletRequest request, HttpServletResponse response) {
        //Permet de recevoir la catégorie sélectionné par l'utilisateur
        String paramCategory = request.getParameter("category");
        //ArrayList<Category> categories = MCategory.getCategories();
        int categorySelected;

        if (paramCategory != null) {
            try {
                //Si l'utilisateur entre lui même une valeur pour le paramêtre category dans la barre d'adresse
                // alors s'il la catégorie est invalide, alors la catégorie sélectionné deviendra 1 (qui représente toutes les catégories)
                categorySelected = Integer.valueOf(paramCategory);
                if (MCategory.isExist(categorySelected) != 1) {
                    categorySelected = 1;
                }
            } catch (NumberFormatException e) {
                categorySelected = 1;
            }
        } else {
            categorySelected = 0;
        }

        return categorySelected;
    }

    public static boolean addCategory(HttpServletRequest request, HttpServletResponse response) {
        String[] c_formParams = {"name", "description", "order", "is_active"};
        String[] c_formValues = {
            request.getParameter(c_formParams[0]),
            request.getParameter(c_formParams[1]),
            request.getParameter(c_formParams[2]),
            request.getParameter(c_formParams[3])
        };

        boolean isCompleted;
        //HashMap des données saisies
        HashMap<String, String> hm_formParams = new HashMap<String, String>();

        for (int i = 0; i < c_formParams.length; i++) {
            hm_formParams.put(c_formParams[i], c_formValues[i]);
        }

        //Validation du formulaire et enregistrement du message d'erreur dans la requête
        isCompleted = validateForm(hm_formParams, request);

        if (isCompleted) {

            Category category = new Category();
            //Category category = new Category();
            category.setId(-1);
            category.setName(hm_formParams.get("name"));
            category.setDescription(hm_formParams.get("description"));
            category.setOrder(Integer.parseInt(hm_formParams.get("order")));
            boolean isActive = hm_formParams.get("is_active") != null;
            category.setIs_active(isActive);

            int newCategory = MCategory.addCategory(category);

            if (newCategory < 1) {
                isCompleted = false;
                if (newCategory == 0) {
                    request.setAttribute("error", "categoryExisting");
                } else if (newCategory == -1) {
                    request.setAttribute("error", "DBProblem");
                }
            }

        }

        if (!isCompleted) {
            request.setAttribute("hm_formParams", hm_formParams);
        }
        return isCompleted;
    }

    public static boolean validateForm(HashMap<String, String> hm_formParams, HttpServletRequest request) {
        //Création d'un HashMap pour contenir les potentiels 
        HashMap<String, String> hm_fieldErrorMsg = new HashMap<String, String>();

        Restriction restrictCat_name = new Restriction(1, 45, Pattern.compile("^([a-zA-ZàéèêâïçÀÉÈÊÏÇ])+([ -][a-zA-ZàéèêâïçÀÉÈÊÏÇ]+)*"));
        Restriction restrictCat_des = new Restriction(1, 45, Pattern.compile("^([a-zA-ZàéèêâïçÀÉÈÊÏÇ])+([ -][a-zA-ZàéèêâïçÀÉÈÊÏÇ]+)*"));
        Restriction restrictCat_order = new Restriction(1, 10, Pattern.compile("^[0-9]+$"));
        //Restriction restrictCat_isActive = new Restriction(1,5,Pattern.compile("[A-Za-z]*"));

        //Création d'un objet Validation et ajout des restrictions à ce dernier
        Validation validation = new Validation(hm_formParams);

        validation.addRestriction("name", restrictCat_name);
        validation.addRestriction("description", restrictCat_des);
        validation.addRestriction("order", restrictCat_order);
        // validation.addRestriction("is_active", restrictCat_isActive);
        //On conserve les résultat des tests
        ArrayList<ResultValidation> resultValidations = validation.validate();
        //On parcours les résultat des tests
        for (ResultValidation rv : resultValidations) {
            //Si le test ne passe pas, alors on ajoute un message d'erreur
            if (rv.getCode() != 0) {
                hm_fieldErrorMsg.put(rv.getKey(), getErrorMsg(rv));
            }
        }

        //On passe le hashMap en attribut à la requête
        request.setAttribute("hm_fieldErrorMsg", hm_fieldErrorMsg);

        return validation.isValidate();
    }

    private static String getErrorMsg(ResultValidation resValid) {
        String errorMsg = "";
        switch (resValid.getKey()) {
            case "name":
                errorMsg += getErrorMsgForName(resValid);
                break;
            case "description":
                errorMsg += getErrorMsgForDescription(resValid);
                break;
            case "order":
                errorMsg += getErrorMsgForOrder(resValid);
                break;
            case "is_active":
                errorMsg += getErrorMsgForIsActive(resValid);
                break;

        }
        return errorMsg;

    }

    private static String getErrorMsgForName(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ nom.\n";
                break;
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
                break;
            case 4:
                errorMsg += "Lettre seulement, pas de chiffre ou de caractère spéciaux. Espace et trait d'union accepté, sauf s'ils ont au début ou à la fin du nom)";
                break;
            case 5:
                break;
        }

        return errorMsg;
    }

    private static String getErrorMsgForDescription(ResultValidation resValid) {
        String errorMsg = "";
        Restriction restriction = resValid.getRestriction();
        switch (resValid.getCode()) {
            case 1:
                errorMsg += "Vous devez remplir le champ nom.\n";
                break;
            case 2:
            case 3:
                errorMsg += "Vous devez saisir entre " + restriction.getMinLength() + " et " + restriction.getMaxLength() + " caractère(s).\n";
                break;
            case 4:
                errorMsg += "Lettre seulement, pas de chiffre ou de caractère spéciaux. Espace et trait d'union acceptés, sauf s'ils ont au début ou à la fin de la description)";
                break;
            case 5:
                break;
        }
        return errorMsg;
    }

    private static String getErrorMsgForOrder(ResultValidation resValid) {
        String errorMsg = "";

        errorMsg += "Veuillez saisir une valeur numérique seulement et assurez-vous qu'elle n'existe pas déjà dans la BD";

        return errorMsg;
    }

    private static String getErrorMsgForIsActive(ResultValidation resValid) {
        String errorMsg = "";

        errorMsg += "Veuillez cocher une des cases pour indiquer si la catégorie est cochée ou pas";

        return errorMsg;
    }

}
