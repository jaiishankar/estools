Ext.define('estools.view.users.DetailPanel', {
    extend: 'Ext.Panel',
    // register the estools.view.process.DetailPanel class with an xtype of processdetailpanel
    alias: 'widget.userdetailpanel',
    // add tplMarkup as a new property
    tplMarkup: [
        'ID: <a href="./v1/users/{id}" target="_blank">{id}</a><br/>',
        'First Name: {fname}<br/>',
        'Last Name: {lname}<br/>',
        'Middle Name: {mname}<br/>',
        'Email: {email}<br/>',
        'Phone: {phone}<br/>',
        '<br/><br/><hr/><b>Double click the row to edit</b><br/>'
    ],
    // startingMarup as a new property
    startingMarkup: '',
    bodyPadding: 5,
    border: 0,
    // override initComponent to create and compile the template
    // apply styles to the body of the panel and initialize
    // html to startingMarkup
    initComponent: function() {
        this.tpl = Ext.create('Ext.Template', this.tplMarkup);
        this.html = this.startingMarkup;

        this.bodyStyle = {
            background: '#ffffff'
        };
        // call the superclass's initComponent implementation
        this.callParent();
    },
    // add a method which updates the details
    updateDetail: function(data) {
        this.update(data);
    }
});