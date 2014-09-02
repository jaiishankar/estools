Ext.define('estools.model.User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'uname', type: 'string'},
        {name: 'passcode', type: 'string'},
        {name: 'lname', type: 'string'},
        {name: 'fname', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'mname', type: 'string'},
        {name: 'isAdminUser', type: 'bool'},
        {name: 'isActive', type: 'int'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: './v1/users',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});