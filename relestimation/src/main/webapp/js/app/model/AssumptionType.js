Ext.define('estools.model.AssumptionType', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'typeName', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: './v1/assumptiontypes',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});