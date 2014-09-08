Ext.define('estools.model.Assumption', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'caseId', type: 'int'},
        {name: 'typeId', type: 'int'},
        {name: 'assumption', type: 'string'},
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