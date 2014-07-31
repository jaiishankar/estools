Ext.define('estools.model.Project', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'ownerid', type: 'int'},
        {name: 'title', type: 'string'},
        {name: 'description', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url:'',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});