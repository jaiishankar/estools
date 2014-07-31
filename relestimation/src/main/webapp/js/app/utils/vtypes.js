/* 
 * Define custom validation types to add to the defaults that come with ExtJS
 */

/**
 * Matches two password fields ensuring they have the same password entered. The
 * validation message should be displayed on the second of the two fields which
 * will reference the first through the property "matchPasswordAgainst".
 */
Ext.apply(Ext.form.field.VTypes, {
    password: function(value, field) {
        return field.matchPasswordAgainst.getValue() === value;
    },
    passwordText: 'Passwords do not match',
    phoneRe: /^(\d{3}-){2}(\d{4})$/,
    phoneMask: /[\d-]/,
    phoneText: 'Not a valid phone number.  Must be in the format 123-456-7890',
    phone: function(v) {
        return Ext.form.VTypes["phoneRe"].test(v);
    },
    login: function() {
        var re = /^[a-zA-Z][-_a-zA-Z0-9]{0,30}$/;
        return function(v) {
            return re.test(v);
        };
    }(),
    loginText: 'Allowed characters are - and _ along with alpha numeric'

});
