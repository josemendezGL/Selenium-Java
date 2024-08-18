package com.example.utils;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.util.Set;

public class SchemaValidator {

    public static void validateJsonSchema(String jsonResponse, String schemaPath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonResponse);

        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();

        // Usar InputStream para cargar el esquema desde el archivo
        try (InputStream schemaStream = SchemaValidator.class.getClassLoader().getResourceAsStream(schemaPath)) {
            if (schemaStream == null) {
                throw new RuntimeException("Schema file not found: " + schemaPath);
            }

            JsonSchema schema = factory.getSchema(schemaStream);

            Set<ValidationMessage> validationMessages = schema.validate(jsonNode);

            if (!validationMessages.isEmpty()) {
                throw new RuntimeException("Schema validation failed: " + validationMessages.toString());
            }
        }
    }
}
