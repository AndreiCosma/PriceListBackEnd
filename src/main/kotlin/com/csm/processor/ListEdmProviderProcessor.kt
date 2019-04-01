package com.csm.processor

import com.csm.exception.OdataException
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind
import org.apache.olingo.commons.api.edm.FullQualifiedName
import org.apache.olingo.commons.api.edm.provider.*


/*
* Created by I503342 - 27/03/2019
*/
/**
 * This class is used to expose the service metadata.
 */
class ListEdmProviderProcessor : CsdlAbstractEdmProvider() {
    companion object {
        /**
         * Service namespace
         */
        const val NAMESPACE = "oData.ListService"
        /**
         * EDM container
         */
        const val CONTAINER_NAME = "ListService.Container"
        val CONTAINER = FullQualifiedName(NAMESPACE, CONTAINER_NAME)
        /**
         * Entity type name
         */
        const val ET_LIST_ITEM_NAME = "ListItem"
        val ET_LIST_ITEM_FQN = FullQualifiedName(NAMESPACE, ET_LIST_ITEM_NAME)
        const val ET_LIST_NAME = "List"
        val ET_LIST_FQN = FullQualifiedName(NAMESPACE, ET_LIST_NAME)
        /**
         * Entity set name
         */
        const val ES_LISTS_NAME = "Lists"
        const val ES_LIST_ITEMS_NAME = "Items"

    }

    /**
     * This method exposes how an DTO entity will look like.
     */
    override fun getEntityType(entityTypeName: FullQualifiedName): CsdlEntityType {
        if (ET_LIST_FQN == entityTypeName) {
            //Expose entity properties
            val idProperty = CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Decimal.fullQualifiedName)
            val nameProperty = CsdlProperty().setName("NAME").setType(EdmPrimitiveTypeKind.String.fullQualifiedName)

            val items = CsdlProperty().setName("ITEMS").setType(ET_LIST_FQN).setCollection(true)

            // Expose entity Key
            val propertyRefKey = CsdlPropertyRef()
            propertyRefKey.name = "ID"

            //Create entity metadata object
            val entityType = CsdlEntityType()
            entityType.name = ET_LIST_NAME

            // Set properties and key
            entityType.properties = arrayListOf(idProperty, nameProperty, items)
            entityType.key = listOf(propertyRefKey)

            return entityType
        } else if (ET_LIST_ITEM_FQN == entityTypeName) {
            //Expose entity properties
            val listIdProperty = CsdlProperty().setName("LIST_ID").setType(EdmPrimitiveTypeKind.Decimal.fullQualifiedName)
            val idProperty = CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Decimal.fullQualifiedName)
            val nameProperty = CsdlProperty().setName("NAME").setType(EdmPrimitiveTypeKind.String.fullQualifiedName)

            val checked = CsdlProperty().setName("CHECKED").setType(EdmPrimitiveTypeKind.Boolean.fullQualifiedName)

            // Expose multiple entity Key
            val propertyRefKeyOne = CsdlPropertyRef()
            propertyRefKeyOne.name = "ID"
            val propertyRefKeyTwo = CsdlPropertyRef()
            propertyRefKeyTwo.name = "LIST_ID"

            //Create entity metadata object
            val entityType = CsdlEntityType()
            entityType.name = ET_LIST_ITEM_NAME

            // Set properties and key
            entityType.properties = arrayListOf(listIdProperty, idProperty, nameProperty, checked)
            entityType.key = listOf(propertyRefKeyOne, propertyRefKeyTwo)

            return entityType
        }
        throw OdataException("No Such entity exists")
    }

    override fun getEntitySet(entityContainer: FullQualifiedName, entitySetName: String): CsdlEntitySet {
        val entitySet = CsdlEntitySet()
        if (entityContainer == CONTAINER) {
            if (entitySetName == ES_LISTS_NAME) {
                return entitySet.setName(ES_LISTS_NAME).setType(ET_LIST_FQN)
            } else if (entitySetName == ES_LIST_ITEMS_NAME) {
                return entitySet.setName(ES_LIST_ITEMS_NAME).setType(ET_LIST_ITEM_FQN)
            }
        }

        throw OdataException("No Such entity exists")
    }

    override fun getEntityContainer(): CsdlEntityContainer {
        val entitySets = arrayListOf<CsdlEntitySet>()
        entitySets.add(getEntitySet(CONTAINER, ES_LISTS_NAME))
        entitySets.add(getEntitySet(CONTAINER, ES_LIST_ITEMS_NAME))

        val entityContainer = CsdlEntityContainer()
        return entityContainer.setName(CONTAINER_NAME).setEntitySets(entitySets)
    }

    override fun getComplexType(complexTypeName: FullQualifiedName): CsdlComplexType {
        return super.getComplexType(complexTypeName)
    }

    override fun getEntityContainerInfo(entityContainerName: FullQualifiedName?): CsdlEntityContainerInfo {
        if (entityContainerName != null || entityContainerName == CONTAINER) {
            return CsdlEntityContainerInfo().setContainerName(CONTAINER)
        }
        throw OdataException("No Such container exists")
    }

    override fun getSchemas(): MutableList<CsdlSchema> {
        val schema = CsdlSchema()

        val entityTypes = arrayListOf<CsdlEntityType>()
        entityTypes.add(getEntityType(ET_LIST_FQN))
        entityTypes.add(getEntityType(ET_LIST_ITEM_FQN))

        schema.entityContainer = entityContainer

        return arrayListOf(schema)
    }


}