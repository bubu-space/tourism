<template>
	<view>
		<scroll-view scroll-x class="bg-white nav">
			<view class="flex text-center">
				<view class="cu-item flex-sub" :class="index==TabCur?'text-green cur':''" v-for="(item,index) in tabList" :key="index" @tap="tabSelect" :data-id="index">
					{{item}}
				</view>
			</view>
		</scroll-view>
		
		<view v-if="dataList.length>0" class="list_container">
			<view class="item" v-for="(item,index) in dataList" :key="index" @click="todetail(item.id)">
				<image :src="item.cover.url"></image>
				<view class="padding-xs">{{item.title}}</view>
			</view>
		</view>
		
		<empty v-else></empty>
		
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	export default {
		data() {
			return {  
				dataList: [],
				tabList: ['美食','住宿','行程','文创产品'],
				type: ['FOOD','LIVE','TRIP','PRODUCTION'],
				TabCur: 0,
				scrollLeft: 0
			}
		},
		onLoad() {
			this.getData()
		},
		methods: {
			getData(){
				let param = {
					type: this.type[this.TabCur],
					current: 1,
					size: 100
				}
				indexApi.getrecommend(param).then(res => {
					this.dataList = res.data
				})	
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
				this.getData()
			},
			todetail(id){
				uni.navigateTo({
					url:'/pages/home/children/recommend/detail?id='+id
				})
			}
		}
	}
</script>


<style lang="scss">
	
	.list_container{
		padding: 14px;
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-gap: 20px;
		
		
		.item{
			height: 160px;
			background-color: #fff;
			border-radius: 4px;
			
			image{
				border-radius: 4px;
				width: 100%;
				height: 130px;
			}
		}
	}
</style>
