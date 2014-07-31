/* 
 *  
 *  This class will used to store all global variables. This is ideally loaded 
 *  in the local storage with the values that are default in this class and will 
 *  be modified and stored in the storage.
 * 
 */

globalvar = {
    updateFromLocal: true,
    currentUserId: 0
};

Ext.define('estools.utils', {
    statics: {
        updateCache: function (data) {
            localStorage.setItem("globalvars", Ext.encode(data)); 
        },
        reloadFromCacheIfExists : function(){
            var instance = Ext.decode(localStorage.getItem("globalvars"));
            if(instance){
                if(globalvar.updateFromLocal === true){
                    globalvar = instance;
                }
            } else {
                console.log(globalvar);
                localStorage.setItem("globalvars", Ext.encode(globalvar)); 
            }
        }
    }
});

