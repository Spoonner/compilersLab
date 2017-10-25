package com.spoonner.compilers.test_lab3_7;

import java.util.*;

public class SyntaxAnalyzer {

    private final String IDENTIFIER_TYPE = "TK_IDENTIFIER";
    private final String VAR = "TK_VAR";
    private List<Token> tokens;
    private Iterator<Token> iter;
    private List<String> errorMessages = new ArrayList<>(20);
    private int currentTokenIndex = 0;

    private Set<String> declaredVariables = new HashSet<>(20);

    public void printErrors() {
        if (errorMessages.isEmpty())
            System.out.println("No errors");
        else
            errorMessages
                    .forEach(System.err::println);
    }

    private boolean verbose = false;


    enum VerboseType {
        SILENT, VERBOSE;
    }

    private boolean isIdentifier() {
        return peekToken().getTokenType().equalsIgnoreCase("TK_IDENTIFIER");
    }

    public void setTokenList(List<Token> tokens) {
        this.tokens = tokens;
        this.iter = tokens.iterator();
    }

    private Token peekToken() {
        if (currentTokenIndex >= tokens.size())
            return null;
        return tokens.get(currentTokenIndex);
    }

    private Token nextToken() {
        currentTokenIndex++;
        return iter.hasNext() ? iter.next() : null;
    }

    public boolean validate(VerboseType type) {
        boolean isValid = true;
        if (type == VerboseType.VERBOSE)
            verbose = true;

        //TODO: add more AND statements as we go
        isValid = programDeclarationBlock()
                && declarationBlock()
                && subProgramDeclaration();
        return isValid;
    }


    private boolean validProgram() {
        boolean valid = true;
        Token currentToken = nextToken();
        if (currentToken.getTokenValue().equalsIgnoreCase("program")) {
        } else {
            if (verbose) {
                String error = String.format("ERROR: Expected program declaration at %d:%d, but found '%s' of type %s", currentToken.getLineRow() + 1,
                        currentToken.getLineCol() + 2, currentToken.getTokenValue(), currentToken.getTokenType());
                errorMessages.add(error);
            }
            valid = false;
        }
        return valid;
    }

    private boolean validId() {
        Token currentToken = nextToken();
        String tokenValue = currentToken.getTokenValue();
        char c = tokenValue.charAt(0);
        String error = null;
        if (Character.isAlphabetic(c) || Character.toString(c).equalsIgnoreCase("_")) {
            for (int i = 1; i < tokenValue.length(); i++) {
                if (!(Character.isAlphabetic(tokenValue.charAt(i)) || Character.isDigit(tokenValue.charAt(i))
                        || Character.toString(tokenValue.charAt(i)).equalsIgnoreCase("$")
                        || Character.toString(tokenValue.charAt(i)).equalsIgnoreCase("_"))) {

                    //error with identifier

                    error = String.format("ERROR: Invalid identifier at %d:%d, invalid symbol at index %d ('%s')", currentToken.getLineRow() + 1,
                            currentToken.getLineCol() + 2,
                            i, tokenValue.charAt(i));

                }
            }
        } else {
            error = String.format("ERROR: Invalid identifier at %d:%d, invalid symbol at index %d ('%s')", currentToken.getLineRow() + 1,
                    currentToken.getLineCol() + 2,
                    0, tokenValue.charAt(0));
            return false;
        }
        if (error != null && verbose) {
            errorMessages.add(error);
            return false;
        } else {
            return true;
        }
    }

    private boolean programDeclarationBlock() {
        boolean valid = validProgram()
                && validId()
                && semiColonPresent();
        return valid;
    }

    private boolean semiColonPresent() {
        Token currentToken = nextToken();
        if (currentToken.getTokenValue().equalsIgnoreCase(";")) {
            return true;
        } else {
            if (verbose) {
                String error = String.format("ERROR: Missing semicolon, but found '%s' at %d:%d",
                        currentToken.getTokenValue(), currentToken.getLineRow() + 1, currentToken.getLineCol() + 2);
                errorMessages.add(error);
            }
            return false;
        }
    }

    private boolean declarationBlock() {
        boolean valid = true;
        boolean expectVariable = false;
        boolean expectCommaColon = false;
        boolean expectSemiColon = false;
        boolean expectType = false;
        boolean expectVarKW = true;
        boolean stopLoop = false;
        int currentRow = 0;

        while (!stopLoop) {
            Token token = nextToken();
            if (expectVarKW) {
                if (token.getTokenType().equalsIgnoreCase(VAR)) {
                    expectVarKW = false;
                    expectVariable = true;
                    continue;
                } else {
                    stopLoop = true;
                }
            } else if (expectCommaColon) {
                if (token.getTokenType().equalsIgnoreCase("TK_COMMA")) {
                    expectCommaColon = false;
                    expectVariable = true;
                    continue;
                } else if (token.getTokenType().equalsIgnoreCase("TK_COLON")) {
                    expectType = true;
                    expectCommaColon = false;
                    continue;
                } else {
                    if (verbose) {
                        String error = String.format("ERROR: Expect ',' or ':', but found '%s' at %d:%d",
                                token.getTokenValue(), token.getLineRow() + 1, token.getLineCol() + 2);
                        errorMessages.add(error);
                    }
                    valid = false;
                    break;
                }
            } else if (expectSemiColon) {
                expectVarKW = true;
                if (token.getTokenType().equalsIgnoreCase("TK_SEMI_COLON")) {
                    expectSemiColon = false;
                    continue;
                } else {
                    valid = false;
                    if (verbose) {
                        String error = String.format("ERROR: Missing ';' at row %d", currentRow + 1);
                        errorMessages.add(error);
                    }
                    continue;
                }
            } else if (expectType) {
                expectType = false;
                expectSemiColon = true;
                if (token.getTokenType().equalsIgnoreCase("TK_INTEGER")
                        || token.getTokenType().equalsIgnoreCase("TK_REAL")
                        || token.getTokenType().equalsIgnoreCase("TK_BOOLEAN")) {

                    currentRow = token.getLineRow();
                    continue;
                } else {
                    valid = false;
                    if (verbose) {
                        String error = String.format("ERROR: Invalid type declaration at %d:%d, " +
                                        "valid types are 'real', 'integer', 'boolean'",
                                token.getLineRow() + 1, token.getLineCol() + 2);
                        errorMessages.add(error);
                    }
                    continue;
                }
            } else if (expectVariable) {
                expectVariable = false;
                if (token.getTokenType().equalsIgnoreCase(IDENTIFIER_TYPE)) {
                    expectCommaColon = true;
                    if (declaredVariables.contains(token.getTokenValue())) {
                        valid = false;
                        if (verbose) {
                            String error = String.format("ERROR: Multiple declaration of '%s' at %d:%d",
                                    token.getTokenValue(), token.getLineRow() + 1, token.getLineCol() + 2);
                            errorMessages.add(error);
                        }
                        continue;
                    } else {
                        declaredVariables.add(token.getTokenValue());
                        continue;
                    }
                } else {
                    valid = false;
                    if (verbose) {
                        String error = String.format("ERROR: Expected variable name, but found '%s' at %d:%d",
                                token.getTokenValue(), token.getLineRow() + 1, token.getLineCol() + 2);
                        errorMessages.add(error);
                    }
                    break;
                }
            }
        }
        return valid;
    }

    private boolean compoundStatement() {
        boolean valid = true;
        boolean stopLoop = false;
        boolean expectBegin = true;
        boolean expectStatementOrEnd = false;
        while (!stopLoop) {
            Token token = peekToken();
            if (expectBegin) {
                if (token.getTokenType().equalsIgnoreCase("TK_BEGIN")) {
                    expectStatementOrEnd = true;
                    expectBegin = false;
                } else {

                }


            } else if (expectStatementOrEnd) {

            }
        }
        return valid;
    }

    private boolean subProgramDeclaration() {
        boolean valid = true;
        boolean stopLoop = false;
        boolean expectProgramKW = true;
        boolean expectIdentifier = false;
        boolean expectSemiColon = false;

        while (!stopLoop) {

        }

        return valid;
    }


    private boolean validIdentifier(String identifier) {
        char c = identifier.charAt(0);
        if (Character.isAlphabetic(c) || Character.toString(c).equalsIgnoreCase("_")) {
            for (int i = 1; i < identifier.length(); i++) {
                if (!(Character.isAlphabetic(identifier.charAt(i)) || Character.isDigit(identifier.charAt(i))
                        || Character.toString(identifier.charAt(i)).equalsIgnoreCase("$")
                        || Character.toString(identifier.charAt(i)).equalsIgnoreCase("_"))) {

                    //error with identifier
                    return false;
          /*          error = String.format("ERROR: Invalid identifier at %d:%d, invalid symbol at index %d ('%s')", currentToken.getLineRow(),
                            currentToken.getLineCol(),
                            i, tokenValue.charAt(i));
*/
                }
            }
        }
        return true;
    }


    private boolean validType(Token token) {
        String tokenValue = token.getTokenValue();
        if (tokenValue.equalsIgnoreCase("integer")
                || tokenValue.equalsIgnoreCase("real")
                || tokenValue.equalsIgnoreCase("boolean"))
            return true;
        else
            return false;
    }

}
