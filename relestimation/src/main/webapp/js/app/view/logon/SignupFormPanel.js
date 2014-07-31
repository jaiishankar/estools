Ext.define('estools.view.logon.SignupFormPanel', {
    extend: 'Ext.form.Panel',
    alias: 'widget.signupformpanel',
    url: './v1/users/signup',
    bodyPadding: 5,
    initComponent: function() {

        // set the login form into the window
        this.items = [
            this.createNameTextfield(),
            this.createPasswordTextfield(),
            this.createRepeatPasswordTextfield(),
            this.createAgreeTermsfield()
        ],
                this.buttons = [
                    this.createCancelSignupButton(),
                    this.createSignupButton()
                ];

        // call the superclass's initComponent implementation
        this.callParent();
    },
    createAgreeTermsfield: function() {

        this.termsFieldField =
                {
                    xtype: 'checkboxfield',
                    name: 'acceptTerms',
                    reference: 'acceptTerms',
                    fieldLabel: 'Terms of Use',
                    hideLabel: true,
                    submitValue: false,
                    margin: '15 0 0 0',
                    boxLabel: 'I have read and accept the <a class="terms">Terms of Use</a>.',
                    // Custom validation logic - requires the checkbox to be checked
                    getErrors: function() {
                        return this.getValue() ? [] : ['You must accept the Terms of Use'];
                    }
                };
        return this.termsFieldField;
    },
    createNameTextfield: function() {

        this.nameTextField = Ext.create('Ext.form.field.Text', {
            name: 'uname',
            fieldLabel: 'Name',
            allowBlank: false
        });
        return this.nameTextField;
    },
    createPasswordTextfield: function() {
        this.passwordTextField = Ext.create('Ext.form.field.Text', {
            name: 'passcode',
            inputType: 'password',
            fieldLabel: 'Password',
            allowBlank: false
        });
        return this.passwordTextField;
    },
    createRepeatPasswordTextfield: function() {
        return {
            xtype: 'textfield',
            name: 'repeatpassword',
            inputType: 'password',
            fieldLabel: 'Repeat Password',
            allowBlank: false,
            vtype: 'password',
            matchPasswordAgainst: this.passwordTextField
        };
    },
    createCancelSignupButton: function() {
        return {
            xtype: 'button',
            action: 'cancelSignup',
            text: 'Cancel'
        };
    },
    createSignupButton: function() {
        return {
            xtype: 'button',
            action: 'signup',
            formBind: true,
            text: 'Signup'
        };
    },
    getNameTextField: function() {
        return this.nameTextField;
    }
});