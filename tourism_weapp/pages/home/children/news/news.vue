<template>
	<view>
		<view class="search">
			<view class="inputbox">
				<view class="cuIcon-search"></view>
				<input type="text" @keyup.enter.native="handleSearch" v-model:value="searchKey" placeholder="搜索标题" />
			</view>
		</view>
		
		<view v-if="dataList.length>0" class="list">
			<view @click="toDetail(item.id)" class="list_item" v-for="(item,index) in dataList" :key="index">
				<image :src="item.image.url" mode="" style="margin-bottom: 10px;"></image>
				<view class="content">
					<view class="h3 title margin-bottom-df">{{item.title}}</view>
					<view class="log">
						<text class="weaktext">浏览量：{{item.viewCount||0}}</text>
						<text class="weaktext">发布时间：{{item.createTime}}</text>
					</view>
				</view>
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
				searchKey: null,
				dataList: []
			}
		},
		onLoad() {
			this.getData()
		},
		methods: {
			getData(){
				let param = {
					title: this.searchKey
				}
				indexApi.getspotactivite(param).then(res => {
					this.dataList = res.data
				})
			},
			handleSearch(){
				this.getData()
			},
			toDetail(id){
				uni.navigateTo({
					url:'/pages/home/children/news/detail?id='+id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.search{
		padding: 10px 14px;
		background-color: #fff;
		
		.inputbox{
			padding: 8px;
			background-color: #F7F7F7;
			display: flex;
			align-items: center;
			gap: 4px;
			border-radius: 4px;
		}
	}

	.list{
		padding: 10px;
		
		.list_item{
			background-color: #fff;
			margin-bottom: 14px;
			
			image{
				width: 100%;
				height: 200px;
			}
			
			.content{
				padding: 14px;
				padding-top: 0;
				
				.abstract{
					display: block;
					margin: 10px 0;
				}
				
				.log{
					display: flex;
					align-items: center;
					justify-content: space-between;
				}
			}
		}
	}


</style>
