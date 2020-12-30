<template>
  <a-card :bordered="false">

  <input id="testInput" type="text" placeholder="请输入" @keyup="textchange($event)">

  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  // import ZnWordsModal from './modules/ZnWordsModal'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'
  import Vue from 'vue' 
  import $ from 'jquery'
  // Vue.use(Element)
  var char = "王";
  export default {
    name: 'TestWords',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      // ZnWordsModal,
      // JSuperQuery,
    },
    data () {
      return {
        description: '汉字对照表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          }
        ],
        url: {
          list: "/words/znWords/list",
          delete: "/words/znWords/delete",
          deleteBatch: "/words/znWords/deleteBatch",
          exportXlsUrl: "/words/znWords/exportXls",
          importExcelUrl: "words/znWords/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
        inputVal:"",
        code:"yyuy",
        char:"王",
      }
    },
    created() {
      $(function(){
        $("#testId").val("");
        $("#result").val("wrong");
        $("#charResult").val("wrong");
    });
    },
    computed: {
      // importExcelUrl: function(){
      //   return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      // },
    },
    methods: {
      init(){
		    
	    },
      textchange()
    {
        if($("#testInput").val() === 'yyuy')
        {
            $("#result").val("right");
            console.log("ok!");
        }

        if($("#testInput").val() === char)
        {
            $("#charResult").val("right");
            console.log("no ok!");
        }
    }
    }
    
  }

</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>