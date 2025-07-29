<template>
	<view>
		<view v-if="dataList.length>0" class="spotList">
			<view class="spot_item margin-bottom-sm bg-white padding-sm" v-for="(item,index) in dataList" :key="index" @click="toDetail(item.id)">
				<image :src="item.image.url" mode=""></image>
				<view class="content flex flex-direction justify-between">
					<view class="h3 title">{{item.title}}</view>
					<view style="margin: 4px 0;">
						<text v-if="item.category=='物质文化遗产'" class="cu-tag sm bg-blue">物质文化遗产</text>
						<text v-if="item.category=='非物质文化遗产'" class="cu-tag sm bg-green">非物质文化遗产</text>
					</view>
					<view class="text-grey">
						{{item.address}}
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
				dataList: [],
				params: {
					size: 20,
					current: 1,
					title: null
				}
			};
		},
		onLoad() {
			indexApi.getheritage(this.params).then(res => {
				this.dataList = res.data.records
			})
		},
		methods: {
			toDetail(id){
				uni.navigateTo({
					url: '/pages/home/children/heritage/detail?id='+id
				})
			}
		}
	}
</script>

<style lang="scss">

	.spotList{
		margin-top: 10px;
		padding: 10px;
		
		.spot_item{
			display: flex;
			gap: 10px;
			
			image{
				width: 80px;
				height: 80px;
				border-radius: 4px;
			}
			
			.content{
				flex: 1;
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				
				.title{
					display: flex;
					align-items: center;
					gap: 8px;
				}
				
				.address{
					margin: 4px 0;
				}
				
				.detail{
					display: flex;
					align-items: center;
					justify-content: space-between;
					
					.price{
						color: red;
						font-weight: bold;
						font-size: 16px
					}
				}
			}
		}
	}
</style>
