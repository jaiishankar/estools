Ext.define('estools.model.DevGroup', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: './v1/devgroups',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});