class DataTransformation {

  /*
    We nee d to map variables to such JSON for camunda
    {"variables":
      {
      "aVariable" : {"value" : "aStringValue", "type": "String"},
      "anotherVariable" : {"value" : true, "type": "Boolean"}
      }
    }
  */
  static generateVariablesFromFormFields(formData) {
    const variables = {
      variables: { }
    };
    Object.keys(formData).forEach((field) => {
      variables.variables[field] = {
        value: formData[field]
      };
    });

    return variables;
  }
}

export default DataTransformation;
